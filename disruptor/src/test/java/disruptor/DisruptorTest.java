package disruptor;

import junit.framework.Assert;

import org.junit.Test;
import org.mchaler.examples.distruptor.Data;
import org.mchaler.examples.distruptor.SimpleDisruptor;

public class DisruptorTest {

	@Test
	public void test() throws InterruptedException {
		SimpleDisruptor disruptor = new SimpleDisruptor();
		long val1, val2;
		
		Data data = disruptor.process(100);

		Data data2 =disruptor.process(101);
		
		data.latch.await();
		data2.latch.await();
		
		Assert.assertEquals(202, data.value);
		
		Assert.assertEquals(204, data2.value);
	}
	
}
