package org.mchaler.examples.distruptor;

import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.EventFactory;

public class Data {

	public long value;
	
	public CountDownLatch latch = new CountDownLatch(1);
	
	public void resetLatch() {
		latch = new CountDownLatch(1);
	}
	
	 public final static EventFactory<Data> EVENT_FACTORY = new EventFactory<Data>() {
	        public Data newInstance() {
	            return new Data();
	        }
	    };
}
