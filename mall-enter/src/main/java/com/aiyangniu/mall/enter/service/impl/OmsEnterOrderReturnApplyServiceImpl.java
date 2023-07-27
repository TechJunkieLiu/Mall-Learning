package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.OmsOrderReturnApplyMapper;
import com.aiyangniu.mall.enter.model.bo.OmsOrderReturnApplyParam;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderReturnApply;
import com.aiyangniu.mall.enter.service.OmsEnterOrderReturnApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 前台订单退货管理实现类
 *
 * @author lzq
 * @date 2023/07/21
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsEnterOrderReturnApplyServiceImpl implements OmsEnterOrderReturnApplyService {

    private final OmsOrderReturnApplyMapper returnApplyMapper;

    @Override
    public int create(OmsOrderReturnApplyParam returnApply) {
        OmsOrderReturnApply realApply = new OmsOrderReturnApply();
        BeanUtils.copyProperties(returnApply, realApply);
        realApply.setCreateTime(new Date());
        realApply.setStatus(0);
        return returnApplyMapper.insert(realApply);
    }
}
