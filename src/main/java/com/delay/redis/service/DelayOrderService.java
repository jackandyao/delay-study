package com.delay.redis.service;

import com.delay.service.IDelayOrderService;
import com.delay.redis.util.CalenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/2/3
 * @ 功能描述
 */
//@Service
public class DelayOrderService implements IDelayOrderService {

    @Autowired
    private RedisTemplate redisTemplate;

    private String key ="DELAY.ORDERID.ONE";

    @Override
    public void product() {
        String orderId = "1010101";
        for (int i=0;i<10;i++){
            redisTemplate.opsForZSet().add(key,orderId+i, CalenderUtil.getCurrentTimeInMills(10));
            System.out.println(CalenderUtil.getCurrentTimeByStr(0)+"添加了一个订单[key:"+key
                    +",score:"+CalenderUtil.getCurrentTimeInMills(10)+",orderid:"+orderId+i+"]");
            if (i%3==0){
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void consumer() {
        while (true){

            Set<TypedTuple<Object>> tuples = redisTemplate.opsForZSet().rangeWithScores(key,0,-1);
            Iterator<TypedTuple<Object>> iterator = tuples.iterator();
            while (iterator.hasNext())
            {
                ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
                double score = typedTuple.getScore();
                String orderId = typedTuple.getValue().toString();
                long currentTime = System.currentTimeMillis();
                if (currentTime>=score){
                    Long row = redisTemplate.opsForZSet().remove(key,orderId);
                    if (row!=null && row>0){
                        System.out.println(CalenderUtil.getCurrentTimeByStr(0)
                                +"消费了一个订单[key:+"+key+"" +",score:"+score+",orderid:"+orderId+ "]");
                    }
                }
            }
        }
    }
}
