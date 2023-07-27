package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.CartProduct;
import com.aiyangniu.mall.enter.model.bo.PromotionProduct;
import com.aiyangniu.mall.enter.model.pojo.SmsCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前台购物车商品管理Mapper
 *
 * @author lzq
 * @date 2023/05/25
 */
public interface EnterProductMapper {

    /**
     * 获取促销商品信息列表
     *
     * @param ids 商品IDS
     * @return 促销商品信息列表
     */
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);

    /**
     * 获取购物车商品信息
     *
     * @param id 购物车ID
     * @return 购物车商品信息
     */
    CartProduct getCartProduct(@Param("id") Long id);

    /**
     * 获取可用优惠券列表
     *
     * @param productId 商品ID
     * @param productCategoryId 商品分类ID
     * @return 可用优惠券列表
     */
    List<SmsCoupon> getAvailableCouponList(@Param("productId") Long productId, @Param("productCategoryId") Long productCategoryId);
}
