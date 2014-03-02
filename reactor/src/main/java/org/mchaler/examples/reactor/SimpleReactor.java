package org.mchaler.examples.reactor;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;
import reactor.event.selector.Selectors;
import reactor.function.Consumer;

public class SimpleReactor {

	
	final static String TOPIC = "test.topic";

	final static String TOPIC2 = "test.topic2";
	
	Reactor reactor = Reactors.reactor()
			.env(EnvironmentLocator.ENV)
			.dispatcher("ringBuffer")
			.get();
	
	Reactor reactor2 = Reactors.reactor()
			.env(EnvironmentLocator.ENV)
			.dispatcher("ringBuffer")
			.get();
	
	public SimpleReactor() {
		
		reactor.on(Selectors.object(TOPIC), new Consumer<Event<Data>>() {

			public void accept(Event<Data> event) {
				event.getData().data += "first";
				
			}
			
			
		});	
		
		
		reactor.link(reactor2).on(Selectors.object(TOPIC), new Consumer<Event<Data>>() {

			public void accept(Event<Data> event) {
				event.getData().data += "second";
				
				event.getData().latch.countDown();
			}
			
			
		});	
		
	}
	
	public void process(Data d) {
		reactor.notify(TOPIC, Event.wrap(d));
	}

}
