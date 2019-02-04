package com.delay.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/2/3
 * @ 功能描述
 */
@Component
@Configuration
public class MQConfig {

    /**
     * 任务交换器
     * @return
     */
    @Bean(name = "orderExchange")
    public TopicExchange  exchange(){
        return new TopicExchange(MQProperties.EXCHANGE_NAME);
    }

    /**
     * 任务处理队列
     */

    @Bean(name = "orderQueue")
    public Queue queueMesssage(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl",10000);//设置过期时间是10s
        args.put("x-dead-letter-exchange",MQProperties.DEAD_EXCHANGE_NAME);
        Queue queue = new Queue(MQProperties.QUEUE_NAME,true,true,false,args);
        return queue;
    }

    /**
     * 死信交换器
     */

    @Bean(name = "orderExchange4Dead")
    public FanoutExchange exchange4Dead(){
        return new FanoutExchange(MQProperties.DEAD_EXCHANGE_NAME);
    }


    /**
     * 死信队列
     */
    @Bean(name = "orderDeadQueue")
    public Queue deadQueueMessage(){
        return new Queue(MQProperties.DEAD_QUEUE_NAME);
    }

    /**
     * 任务交换器绑定任务队列
     */
    @Bean
    public Binding bindingExchangeMessage(@Qualifier("orderQueue")Queue queueMessage,
                                          @Qualifier("orderExchange")TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with(MQProperties.ROUTE_KEY);
    }

    /**
     * 死信交换器绑定死心队列
     */
    @Bean
    public Binding bindingExchangeMessage4Dead(@Qualifier("orderDeadQueue")Queue queueMessage,
                                              FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueMessage).to(fanoutExchange);
    }

}
