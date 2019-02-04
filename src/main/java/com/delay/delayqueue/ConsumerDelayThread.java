package com.delay.delayqueue;

import java.util.concurrent.DelayQueue;

public  class ConsumerDelayThread implements Runnable{
        DelayQueue<OrderDelayItem> consumerDelayQueue;

        public ConsumerDelayThread(DelayQueue<OrderDelayItem> consumerDelayQueue){
            this.consumerDelayQueue = consumerDelayQueue;
        }
        @Override
        public void run() {
            while (true){
                OrderDelayItem orderDelayItem = null;
                try {
                    orderDelayItem = consumerDelayQueue.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (orderDelayItem!=null){
                    System.out.println(DateUtil.getSecond()
                            + " Thread" +Thread.currentThread()+ " 消费了一个dealyqueue.orderid: "+orderDelayItem.getOrderID());

                }
                else {
                    System.out.println("目前还没有需要处理的延迟任务");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }