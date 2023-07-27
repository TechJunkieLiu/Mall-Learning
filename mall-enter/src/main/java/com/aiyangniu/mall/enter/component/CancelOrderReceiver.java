package com.aiyangniu.mall.enter.component;

import com.aiyangniu.mall.enter.service.OmsEnterOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的接收者
 *
 * @author lzq
 * @date 2023/05/25
 */
@Slf4j
@Component
@RabbitListener(queues = "mall.order.cancel")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CancelOrderReceiver {

    private final OmsEnterOrderService omsEnterOrderService;

    @RabbitHandler
    public void handle(Long orderId){
        omsEnterOrderService.cancelOrder(orderId);
        log.info("process orderId:{}", orderId);
    }
}
