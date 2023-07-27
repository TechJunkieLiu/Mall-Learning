package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.enter.mapper.HomeMapper;
import com.aiyangniu.mall.enter.mapper.PmsBrandMapper;
import com.aiyangniu.mall.enter.mapper.PmsProductMapper;
import com.aiyangniu.mall.enter.model.pojo.PmsBrand;
import com.aiyangniu.mall.enter.model.pojo.PmsProduct;
import com.aiyangniu.mall.enter.service.PmsEnterBrandService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台商品品牌管理实现类
 *
 * @author lzq
 * @date 2023/07/21
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PmsEnterBrandServiceImpl implements PmsEnterBrandService {

    private final HomeMapper homeMapper;
    private final PmsBrandMapper brandMapper;
    private final PmsProductMapper productMapper;

    @Override
    public List<PmsBrand> recommendList(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return homeMapper.getRecommendBrandList(offset, pageSize);
    }

    @Override
    public PmsBrand detail(Long brandId) {
        return brandMapper.selectById(brandId);
    }

    @Override
    public CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PmsProduct> productList = productMapper.selectList(new LambdaQueryWrapper<PmsProduct>()
                .eq(PmsProduct::getDeleteStatus, 0)
                .eq(PmsProduct::getPublishStatus, 1)
                .eq(PmsProduct::getBrandId, brandId));
        return CommonPage.restPage(productList);
    }
}
