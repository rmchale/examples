package org.mchaler.examples.distruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class SimpleDisruptor {
    final ExecutorService exec = Executors.newCachedThreadPool();
    final static int SIZE =1024; 
    
    
    Disruptor<Data> disruptor = new Disruptor<Data>(Data.EVENT_FACTORY, SIZE, exec);
   
    RingBuffer<Data> ringBuffer;
    
    
    final EventHandler<Data> handler = new EventHandler<Data>() {
        // event will eventually be recycled by the Disruptor after it wraps
        public void onEvent(final Data event, final long sequence, final boolean endOfBatch) throws Exception {
            event.value += 1;
            System.out.println("Sequence: " + sequence);
            System.out.println("First: " + event.value);

        }
    };
    
    final EventHandler<Data> handler2 = new EventHandler<Data>() {
        // event will eventually be recycled by the Disruptor after it wraps
        public void onEvent(final Data event, final long sequence, final boolean endOfBatch) throws Exception {
          
            event.value = event.value * 2;
            System.out.println("Sequence: " + sequence);
            System.out.println("Second: " + event.value);
            
            event.latch.countDown();
        }
    };
    
    public SimpleDisruptor() {
    	disruptor.handleEventsWith(handler);
    	disruptor.after(handler).handleEventsWith(handler2);
    	
    	ringBuffer  = disruptor.start();
    }
    
    public Data process(long value) {
    	  long seq = ringBuffer.next();
    	  Data data = ringBuffer.get(seq);
          data.value = value;
          ringBuffer.publish(seq);
          
          return data;
    }
}
