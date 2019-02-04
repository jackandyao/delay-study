package com.delay.rabbitmq;

import com.delay.redis.util.CalenderUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/2/3
 * @ 功能描述
 */
@Component
public class MQBusiness {
    @RabbitListener(queues = MQProperties.DEAD_QUEUE_NAME)
    public void process(String message){
        System.out.println(CalenderUtil.getCurrentTimeByStr(0)+"消费了一个订单,订单ID:"+message);

    }
}
