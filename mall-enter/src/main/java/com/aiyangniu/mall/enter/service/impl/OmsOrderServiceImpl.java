package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.OmsOrderMapper;
import com.aiyangniu.mall.enter.mapper.OmsOrderOperateHistoryMapper;
import com.aiyangniu.mall.enter.model.bo.OmsOrderDetail;
import com.aiyangniu.mall.enter.model.dto.OmsMoneyInfoParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsOrderDeliveryParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsOrderQueryParamDTO;
import com.aiyangniu.mall.enter.model.dto.OmsReceiverInfoParamDTO;
import com.aiyangniu.mall.enter.model.pojo.OmsOrder;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderOperateHistory;
import com.aiyangniu.mall.enter.service.OmsOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单管理实现类
 *
 * @author lzq
 * @date 2023/06/01
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OmsOrderServiceImpl implements OmsOrderService {

    private final OmsOrderMapper omsOrderMapper;
    private final OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;

    @Override
    public List<OmsOrder> list(OmsOrderQueryParamDTO queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return omsOrderMapper.getList(queryParam);
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParamDTO> deliveryParamList) {
        // 批量发货
        int count = omsOrderMapper.delivery(deliveryParamList);
        // 添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        omsOrderOperateHistoryMapper.insertList(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        int count = omsOrderMapper.update(record, new LambdaQueryWrapper<OmsOrder>().eq(OmsOrder::getDeleteStatus, 0).in(OmsOrder::getId, ids));
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:" + note);
            return history;
        }).collect(Collectors.toList());
        omsOrderOperateHistoryMapper.insertList(historyList);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrder record = new OmsOrder();
        record.setDeleteStatus(1);
        return  omsOrderMapper.update(record, new LambdaQueryWrapper<OmsOrder>().eq(OmsOrder::getDeleteStatus, 0).in(OmsOrder::getId, ids));
    }

    @Override
    public OmsOrderDetail detail(Long id) {
        return omsOrderMapper.getDetail(id);
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParamDTO receiverInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(new Date());
        int count = omsOrderMapper.updateById(order);
        // 插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        omsOrderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParamDTO moneyInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(new Date());
        int count = omsOrderMapper.updateById(order);
        // 插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        omsOrderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = omsOrderMapper.updateById(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        omsOrderOperateHistoryMapper.insert(history);
        return count;
    }
}
