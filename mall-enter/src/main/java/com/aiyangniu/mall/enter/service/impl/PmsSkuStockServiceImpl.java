package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aiyangniu.mall.enter.mapper.PmsSkuStockMapper;
import com.aiyangniu.mall.enter.model.pojo.PmsSkuStock;
import com.aiyangniu.mall.enter.service.PmsSkuStockService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品SKU库存管理实现类
 *
 * @author lzq
 * @date 2023/06/01
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PmsSkuStockServiceImpl implements PmsSkuStockService {

    private final PmsSkuStockMapper pmsSkuStockMapper;

    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        return pmsSkuStockMapper.selectList(new LambdaQueryWrapper<PmsSkuStock>().eq(PmsSkuStock::getProductId, pid).like(!StrUtil.isEmpty(keyword), PmsSkuStock::getSkuCode, keyword));
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        return pmsSkuStockMapper.replaceList(skuStockList);
    }
}
