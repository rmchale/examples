package reactor;

import junit.framework.Assert;
import junit.framework.ComparisonFailure;

import org.junit.Test;
import org.mchaler.examples.reactor.Data;
import org.mchaler.examples.reactor.PromiseReactor;
import org.mchaler.examples.reactor.SimpleReactor;
import org.mchaler.examples.reactor.SimpleReactorWithPromise;

import reactor.core.composable.Promise;

public class ReactorTest {

	@Test
	public void test() throws InterruptedException {
		SimpleReactor r = new SimpleReactor();
		Data d1 = new Data();
		d1.data = "1";
		
		Data d2 = new Data();
		d2.data = "2";
		
		r.process(d1);
		r.process(d2);
		
		d1.latch.await();
		d2.latch.await();
		
		Assert.assertEquals("1firstsecond", d1.data);
		Assert.assertEquals("2firstsecond", d2.data);

	}
	
	@Test(expected=ComparisonFailure.class)
	public void promise() throws InterruptedException {
		PromiseReactor  r = new PromiseReactor();
		Promise<Data> p =  r.load("1");
		p.await();
		
		Assert.assertEquals("1firstsecond", p.get().data);
	}
	
	@Test
	public void simpleWithPromise() throws InterruptedException {
		SimpleReactorWithPromise r = new SimpleReactorWithPromise();
		Data d1 = new Data();
		d1.data = "1";
		
		Promise<Data> p = r.process(d1);
		p.await();
		
		Assert.assertEquals("1firstsecond", p.get().data);
		
		
		
 	}
}
