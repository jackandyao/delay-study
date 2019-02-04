package com.delay.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/1/28
 * @ 功能描述
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<OrderDelayItem> delayQueue = new DelayQueue<OrderDelayItem>();
        new Thread(new ConsumerDelayThread(delayQueue)).start();
        new Thread(new ProducerDelayThread(delayQueue,3000)).start();
    }
}
