package com.delay;

import com.delay.service.IDelayOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

	@Autowired
	private IDelayOrderService delayOrderService;

	private CountDownLatch cdl = new CountDownLatch(1);


	@Test
	public void produce(){
		delayOrderService.product();
		try {
			Thread.sleep(1000);//模拟程序不让其中断
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void consumer(){
		for (int i=0;i<1;i++){
			new Thread(new DelayThread()).start();
			cdl.countDown();
		}
		try {
			Thread.sleep(100000);//模拟程序不让其中断
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private class DelayThread implements Runnable{
		@Override
		public void run() {
			try {
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			delayOrderService.consumer();
		}
	}

}

