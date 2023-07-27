package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.OmsOrderReturnApplyMapper;
import com.aiyangniu.mall.enter.model.bo.OmsOrderReturnApplyResult;
import com.aiyangniu.mall.enter.model.dto.OmsReturnApplyQueryParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsUpdateStatusParamDTO;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderReturnApply;
import com.aiyangniu.mall.enter.service.OmsOrderReturnApplyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单退货申请管理实现类
 *
 * @author lzq
 * @date 2023/05/24
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {

    private final OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;

    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParamDTO queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return omsOrderReturnApplyMapper.getList(queryParam);
    }

    @Override
    public int delete(List<Long> ids) {
        return omsOrderReturnApplyMapper.delete(new LambdaQueryWrapper<OmsOrderReturnApply>().in(OmsOrderReturnApply::getId, ids).eq(OmsOrderReturnApply::getStatus, 3));
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParamDTO statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        switch (status){
            case 1:
                // 确认退货
                returnApply.setId(id);
                returnApply.setStatus(1);
                returnApply.setReturnAmount(statusParam.getReturnAmount());
                returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
                returnApply.setHandleTime(new Date());
                returnApply.setHandleMan(statusParam.getHandleMan());
                returnApply.setHandleNote(statusParam.getHandleNote());
                break;
            case 2:
                // 完成退货
                returnApply.setId(id);
                returnApply.setStatus(2);
                returnApply.setReceiveTime(new Date());
                returnApply.setReceiveMan(statusParam.getReceiveMan());
                returnApply.setReceiveNote(statusParam.getReceiveNote());
                break;
            case 3:
                // 拒绝退货
                returnApply.setId(id);
                returnApply.setStatus(3);
                returnApply.setHandleTime(new Date());
                returnApply.setHandleMan(statusParam.getHandleMan());
                returnApply.setHandleNote(statusParam.getHandleNote());
                break;
            default:
                return 0;
        }
        return omsOrderReturnApplyMapper.updateById(returnApply);
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return omsOrderReturnApplyMapper.getDetail(id);
    }
}
