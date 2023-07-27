package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.enter.mapper.*;
import com.aiyangniu.mall.enter.model.bo.PmsEnterProductDetail;
import com.aiyangniu.mall.enter.model.bo.PmsProductCategoryNode;
import com.aiyangniu.mall.enter.model.pojo.*;
import com.aiyangniu.mall.enter.service.PmsEnterProductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前台商品管理实现类
 *
 * @author lzq
 * @date 2023/07/21
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PmsEnterProductServiceImpl implements PmsEnterProductService {

    private final PmsProductMapper productMapper;
    private final PmsProductCategoryMapper productCategoryMapper;
    private final PmsBrandMapper brandMapper;
    private final PmsProductAttributeMapper productAttributeMapper;
    private final PmsProductAttributeValueMapper productAttributeValueMapper;
    private final PmsSkuStockMapper skuStockMapper;
    private final PmsProductLadderMapper productLadderMapper;
    private final PmsProductFullReductionMapper productFullReductionMapper;
    private final EnterProductMapper enterProductMapper;

    @Override
    public List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        PageHelper.startPage(pageNum, pageSize);

        LambdaQueryWrapper<PmsProduct> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StrUtil.isNotEmpty(keyword), PmsProduct::getName, keyword)
                .eq(brandId != null, PmsProduct::getBrandId, brandId)
                .eq(productCategoryId != null, PmsProduct::getProductCategoryId, productCategoryId);
        // 1->按新品；2->按销量；3->价格从低到高；4->价格从高到低
        switch (sort){
            case 1:
                lambdaQueryWrapper.orderByDesc(PmsProduct::getId);
                break;
            case 2:
                lambdaQueryWrapper.orderByDesc(PmsProduct::getSale);
                break;
            case 3:
                lambdaQueryWrapper.orderByAsc(PmsProduct::getPrice);
                break;
            case 4:
                lambdaQueryWrapper.orderByDesc(PmsProduct::getPrice);
                break;
            default:
                break;
        }
        return productMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public List<PmsProductCategoryNode> categoryTreeList() {
        List<PmsProductCategory> list = productCategoryMapper.selectList(new LambdaQueryWrapper<>());
        return list.stream()
                .filter(item -> item.getParentId().equals(0L))
                .map(item -> covert(item, list))
                .collect(Collectors.toList());
    }

    @Override
    public PmsEnterProductDetail detail(Long id) {
        PmsEnterProductDetail result = new PmsEnterProductDetail();
        // 获取商品信息
        PmsProduct product = productMapper.selectById(id);
        result.setProduct(product);
        // 获取品牌信息
        PmsBrand brand = brandMapper.selectById(product.getBrandId());
        result.setBrand(brand);
        // 获取商品属性信息
        List<PmsProductAttribute> productAttributeList = productAttributeMapper.selectList(new LambdaQueryWrapper<PmsProductAttribute>().eq(PmsProductAttribute::getProductAttributeCategoryId, product.getProductAttributeCategoryId()));
        result.setProductAttributeList(productAttributeList);
        // 获取商品属性值信息
        if(CollUtil.isNotEmpty(productAttributeList)){
            List<Long> attributeIds = productAttributeList.stream().map(PmsProductAttribute::getId).collect(Collectors.toList());
            List<PmsProductAttributeValue> productAttributeValueList = productAttributeValueMapper.selectList(new LambdaQueryWrapper<PmsProductAttributeValue>().eq(PmsProductAttributeValue::getProductId, product.getId()).in(PmsProductAttributeValue::getProductAttributeId, attributeIds));
            result.setProductAttributeValueList(productAttributeValueList);
        }
        // 获取商品SKU库存信息
        List<PmsSkuStock> skuStockList = skuStockMapper.selectList(new LambdaQueryWrapper<PmsSkuStock>().eq(PmsSkuStock::getProductId, product.getId()));
        result.setSkuStockList(skuStockList);
        // 商品阶梯价格设置
        if(product.getPromotionType() == 3){
            List<PmsProductLadder> productLadderList = productLadderMapper.selectList(new LambdaQueryWrapper<PmsProductLadder>().eq(PmsProductLadder::getProductId, product.getId()));
            result.setProductLadderList(productLadderList);
        }
        // 商品满减价格设置
        if(product.getPromotionType() == 4){
            List<PmsProductFullReduction> productFullReductionList = productFullReductionMapper.selectList(new LambdaQueryWrapper<PmsProductFullReduction>().eq(PmsProductFullReduction::getProductId, product.getId()));
            result.setProductFullReductionList(productFullReductionList);
        }
        // 商品可用优惠券
        result.setCouponList(enterProductMapper.getAvailableCouponList(product.getId(), product.getProductCategoryId()));
        return result;
    }

    /**
     * 初始对象转化为节点对象
     */
    private PmsProductCategoryNode covert(PmsProductCategory item, List<PmsProductCategory> list) {
        PmsProductCategoryNode node = new PmsProductCategoryNode();
        BeanUtils.copyProperties(item, node);
        List<PmsProductCategoryNode> children = list.stream()
                .filter(subItem -> subItem.getParentId().equals(item.getId()))
                .map(subItem -> covert(subItem, list))
                .collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
