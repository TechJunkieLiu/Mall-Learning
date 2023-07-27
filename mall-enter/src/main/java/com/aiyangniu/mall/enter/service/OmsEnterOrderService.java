package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.common.api.CommonPage;
import com.aiyangniu.mall.enter.model.bo.ConfirmOrderResult;
import com.aiyangniu.mall.enter.model.bo.OmsOrderDetail;
import com.aiyangniu.mall.enter.model.bo.OrderParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 前台订单管理接口
 *
 * @author lzq
 * @date 2023/05/09
 */
public interface OmsEnterOrderService {

    /**
     * 自动取消超时订单
     *
     * @return 取消数量
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    Integer cancelTimeOutOrder();

    /**
     * 根据用户购物车信息生成确认单信息
     *
     * @param cartIds 购物车ID
     * @return 确认单信息
     */
    ConfirmOrderResult generateConfirmOrder(List<Long> cartIds);

    /**
     * 根据提交信息生成订单
     *
     * @param orderParam 生成订单入参
     * @return 返回
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    Map<String, Object> generateOrder(OrderParam orderParam);

    /**
     * 发送延迟消息取消订单
     *
     * @param orderId 订单编号
     */
    void sendDelayMessageCancelOrder(Long orderId);

    /**
     * 支付成功后的回调
     *
     * @param orderId 订单ID
     * @param payType 支付方式
     * @return 恢复所有下单商品的库存数量
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    Integer paySuccess(Long orderId, Integer payType);

    /**
     * 分页获取用户订单
     *
     * @param status 状态
     * @param pageNum 当前页
     * @param pageSize 页条数
     * @return 用户订单列表
     */
    CommonPage<OmsOrderDetail> list(Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据订单ID获取订单详情
     *
     * @param orderId 订单ID
     * @return 订单详情
     */
    OmsOrderDetail detail(Long orderId);

    /**
     * 取消单个超时订单
     *
     * @param orderId 订单ID
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    void cancelOrder(Long orderId);

    /**
     * 确认收货
     *
     * @param orderId 订单ID
     */
    void confirmReceiveOrder(Long orderId);

    /**
     * 用户根据订单ID删除订单
     *
     * @param orderId 订单ID
     */
    void deleteOrder(Long orderId);
}
