package com.delay.delayqueue;

import java.util.Calendar;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/1/28
 * @ 功能描述 延迟订单存储数据结构
 */
public class OrderDelayItem  implements Delayed{

    /**
     * 订单id
     */
    private String orderID;

    public String getOrderID() {
        return orderID;
    }

    /**
     * 订单过期间隔时间
     */
    private long expireTime=0;


    public OrderDelayItem(String orderID,long delayTime){
        this.orderID = orderID;
        //过期时间 = 当前时间+时间间隔
        expireTime = System.currentTimeMillis() + delayTime;    //到期时间 = 当前时间+延迟时间
    }

    /**
     * 获得延迟时间,用过期时间减去当前时间,当这个值为0的时候,才会BEI延迟队列消费
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        Calendar calendar = Calendar.getInstance();
        return expireTime - calendar.getTimeInMillis();
    }

    /**
     * 用于延迟队列内部比较排序 当前时间的延迟时间 减去 比较对象的延迟时间
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }
}
