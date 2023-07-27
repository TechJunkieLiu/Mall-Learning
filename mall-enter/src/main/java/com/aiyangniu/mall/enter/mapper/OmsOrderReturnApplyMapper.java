package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.bo.OmsOrderReturnApplyResult;
import com.aiyangniu.mall.enter.model.dto.OmsReturnApplyQueryParamDTO;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderReturnApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请管理Mapper
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface OmsOrderReturnApplyMapper extends BaseMapper<OmsOrderReturnApply> {

    /**
     * 查询申请列表
     *
     * @param queryParam 查询参数
     * @return 申请列表
     */
    List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParamDTO queryParam);

    /**
     * 获取申请详情
     *
     * @param id 退货申请单号
     * @return 货申请详情
     */
    OmsOrderReturnApplyResult getDetail(@Param("id") Long id);
}
