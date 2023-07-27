package com.aiyangniu.mall.enter.component;

import com.aiyangniu.mall.common.annotation.RedisLock;
import com.aiyangniu.mall.enter.service.OmsEnterOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 取消超时订单并解锁库存的定时器
 *
 * @author lzq
 * @date 2023/05/09
 */
@Slf4j
//@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderTimeOutCancelTask {

    private final OmsEnterOrderService omsEnterOrderService;

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描超时未支付订单，进行取消操作
     */
//    @RedisLock(lockKey = "cancelTimeOutOrder", timeOut = 8)
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder(){
        Integer count = omsEnterOrderService.cancelTimeOutOrder();
        log.info("取消订单，并根据sku编号释放锁定库存，取消订单数量：{}", count);
    }
}
