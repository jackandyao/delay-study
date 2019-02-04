package com.delay.rabbitmq;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/2/3
 * @ 功能描述
 */
public class MQProperties {
    public static final String EXCHANGE_NAME ="order.orderExchange";
    public static final String DEAD_EXCHANGE_NAME ="order.dead.orderExchange";
    public static final String ROUTE_KEY ="order.routeKey";
    public static final String QUEUE_NAME ="order.orderQueue";
    public static final String DEAD_QUEUE_NAME ="order.dead.orderQueue";
}
