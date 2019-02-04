package com.delay.service;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/2/3
 * @ 功能描述 订单延时处理取消服务接口
 */
public interface IDelayOrderService {
    /**
     * 产生延时订单
     */
    void product();

    /**
     * 处理延时订单
     */
    void consumer();
}
