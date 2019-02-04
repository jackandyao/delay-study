package com.delay.delayqueue;

import java.util.concurrent.DelayQueue;

public  class ProducerDelayThread implements Runnable{
        private DelayQueue<OrderDelayItem> delayQueue;
        private long delayTime;

        public ProducerDelayThread (DelayQueue<OrderDelayItem> delayQueue, long delayTime){
            this.delayQueue = delayQueue;
            this.delayTime = delayTime;
        }

        @Override
        public void run() {
            String orderid ="10001";
            for (int i=0;i<10;i++){
                OrderDelayItem orderDelayItem = new OrderDelayItem(orderid+i,this.delayTime);
                delayQueue.add(orderDelayItem);
                System.out.println(DateUtil.getSecond()
                        + " Thread" +Thread.currentThread()+ " 添加了一个dealyqueue.orderid: "+orderDelayItem.getOrderID());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }