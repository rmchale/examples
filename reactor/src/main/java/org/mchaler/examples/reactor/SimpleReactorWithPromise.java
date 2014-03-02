package org.mchaler.examples.reactor;

import reactor.core.Reactor;
import reactor.core.composable.Deferred;
import reactor.core.composable.Promise;
import reactor.core.composable.spec.Promises;
import reactor.core.spec.Reactors;
import reactor.event.Event;
import reactor.event.selector.Selectors;
import reactor.function.Consumer;

public class SimpleReactorWithPromise {

	
	final static String TOPIC = "test.topic";

	final static String TOPIC2 = "test.topic2";
	
	final Deferred<Data, Promise<Data>> DEFERRED = Promises.defer(EnvironmentLocator.ENV);

	
	Reactor reactor = Reactors.reactor()
			.env(EnvironmentLocator.ENV)
			.dispatcher("ringBuffer")
			.get();
	
	Reactor reactor2 = Reactors.reactor()
			.env(EnvironmentLocator.ENV)
			.dispatcher("ringBuffer")
			.get();
	
	public SimpleReactorWithPromise() {
		
		reactor.on(Selectors.object(TOPIC), new Consumer<Event<Data>>() {

			public void accept(Event<Data> event) {
				event.getData().data += "first";
				
			}
			
			
		});	
		
		
		reactor.link(reactor2).on(Selectors.object(TOPIC), new Consumer<Event<Data>>() {

			public void accept(Event<Data> event) {
				event.getData().data += "second";
				
				DEFERRED.accept(event.getData());
			}
			
			
		});	
		
	}
	
	public Promise<Data> process(Data d) {
		
		Promise<Data> p = DEFERRED.compose();
		
		reactor.notify(TOPIC, Event.wrap(d));
		
		return p;
	}

}
