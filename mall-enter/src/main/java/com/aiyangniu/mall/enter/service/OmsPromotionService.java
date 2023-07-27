package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.bo.CartPromotionItem;
import com.aiyangniu.mall.enter.model.pojo.OmsCartItem;

import java.util.List;

/**
 * 促销管理接口
 *
 * @author lzq
 * @date 2023/05/24
 */
public interface OmsPromotionService {

    /**
     * 计算购物车中的促销活动信息
     *
     * @param cartItemList 购物车
     * @return 促销活动信息
     */
    List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}
