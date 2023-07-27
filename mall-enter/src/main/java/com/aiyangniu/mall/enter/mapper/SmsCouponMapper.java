package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.dto.SmsCouponParamDTO;
import com.aiyangniu.mall.enter.model.pojo.SmsCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 优惠券Mapper
 *
 * @author lzq
 * @date 2023/05/31
 */
public interface SmsCouponMapper extends BaseMapper<SmsCoupon> {

    /**
     * 获取优惠券详情包括绑定关系
     *
     * @param id 优惠券ID
     * @return 优惠券详情
     */
    SmsCouponParamDTO getItem(@Param("id") Long id);
}
