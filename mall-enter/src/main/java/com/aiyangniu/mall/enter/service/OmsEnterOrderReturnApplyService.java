package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.bo.OmsOrderReturnApplyParam;

/**
 * 前台订单退货管理接口
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface OmsEnterOrderReturnApplyService {

    /**
     * 提交申请
     *
     * @param returnApply 退货申请请求参数
     * @return 提交个数
     */
    int create(OmsOrderReturnApplyParam returnApply);
}
