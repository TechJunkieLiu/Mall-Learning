package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.bo.OmsOrderReturnApplyResult;
import com.aiyangniu.mall.enter.model.dto.OmsReturnApplyQueryParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsUpdateStatusParamDTO;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderReturnApply;

import java.util.List;

/**
 * 订单退货申请管理接口
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface OmsOrderReturnApplyService {

    /**
     * 分页查询申请
     *
     * @param queryParam 查询参数
     * @param pageSize 页条数
     * @param pageNum 当前页
     * @return 退货申请列表
     */
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParamDTO queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     *
     * @param ids 属性IDS
     * @return 删除个数
     */
    int delete(List<Long> ids);

    /**
     * 修改指定申请状态
     *
     * @param id 退货申请单号
     * @param statusParam 申请状态
     * @return 修改个数
     */
    int updateStatus(Long id, OmsUpdateStatusParamDTO statusParam);

    /**
     * 获取指定申请详情
     *
     * @param id 退货申请单号
     * @return 申请详情
     */
    OmsOrderReturnApplyResult getItem(Long id);
}
