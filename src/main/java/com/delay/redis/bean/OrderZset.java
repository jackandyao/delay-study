package com.delay.redis.bean;

import org.springframework.data.redis.core.ZSetOperations;

public class OrderZset implements ZSetOperations.TypedTuple<String>{
      String orderId;
      double score;
      public OrderZset(String orderId, double score) {
         this.orderId = orderId;
         this.score = score;
      }
      @Override
      public String getValue() {
         return orderId;
      }
      @Override
      public Double getScore() {
         return score;
      }
      @Override
      public int compareTo(ZSetOperations.TypedTuple<String> o) {
         OrderZset o1 = (OrderZset) o;
         return new Double(this.score).compareTo(o1.getScore());
      }
   }