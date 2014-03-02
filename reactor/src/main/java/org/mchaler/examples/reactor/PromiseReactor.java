package org.mchaler.examples.reactor;

import reactor.core.composable.Deferred;
import reactor.core.composable.Promise;
import reactor.core.composable.spec.Promises;
import reactor.function.Consumer;

public class PromiseReactor {

	 Deferred<Data, Promise<Data>> deferred = Promises.defer(EnvironmentLocator.ENV);

	 
	public Promise<Data> load(String val) {  
	   
		
		Data d = new Data();
		d.data = val;
		
		Promise<Data> p = deferred.compose().consume(new Consumer<Data>() {

			public void accept(Data t) {
				t.data += "first";
				System.out.println("first");
			}
			
		}).consume(new Consumer<Data>(){

			public void accept(Data t) {
				t.data +="second";
				System.out.println("second");

			}
			
		});
			
		
		
		deferred.accept(d);
		
		return p;
	   
	  }
}
