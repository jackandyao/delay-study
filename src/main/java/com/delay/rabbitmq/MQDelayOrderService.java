package com.delay.rabbitmq;

import com.delay.service.IDelayOrderService;
import com.delay.redis.util.CalenderUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/2/3
 * @ 功能描述
 */
@Service
public class MQDelayOrderService implements IDelayOrderService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void product() {
        String orderId = "1010101";
        for (int i=0;i<10;i++){
            amqpTemplate.convertAndSend(MQProperties.EXCHANGE_NAME,MQProperties.ROUTE_KEY,orderId+i);
            System.out.println(CalenderUtil.getCurrentTimeByStr(0)+"添加了一个订单,订单ID:"+orderId+i);
            if (i%3==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void consumer() {

    }
}
