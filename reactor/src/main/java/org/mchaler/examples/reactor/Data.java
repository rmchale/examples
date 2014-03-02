package org.mchaler.examples.reactor;

import java.util.concurrent.CountDownLatch;

public class Data {

	public String data;
	
	public CountDownLatch latch = new CountDownLatch(1);
}
