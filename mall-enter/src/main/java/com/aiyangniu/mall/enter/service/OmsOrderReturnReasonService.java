package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.OmsOrderReturnReason;

import java.util.List;

/**
 * 订单退货原因管理接口
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface OmsOrderReturnReasonService {

    /**
     * 添加退货原因
     *
     * @param returnReason 退货原因
     * @return 添加个数
     */
    int create(OmsOrderReturnReason returnReason);

    /**
     * 修改退货原因
     *
     * @param id 退货订单ID
     * @param returnReason 退货原因
     * @return 修改个数
     */
    int update(Long id, OmsOrderReturnReason returnReason);

    /**
     * 批量删除退货原因
     *
     * @param ids 退货订单IDS
     * @return 删除个数
     */
    int delete(List<Long> ids);

    /**
     * 分页获取退货原因
     *
     * @param pageSize 页条数
     * @param pageNum 当前页
     * @return 退货原因列表
     */
    List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum);

    /**
     * 批量修改退货原因状态
     *
     * @param ids 退货订单IDS
     * @param status 退货原因状态
     * @return 修改个数
     */
    int updateStatus(List<Long> ids, Integer status);

    /**
     * 获取单个退货原因详情信息
     *
     * @param id 退货订单ID
     * @return 退货原因详情信息
     */
    OmsOrderReturnReason getItem(Long id);
}
