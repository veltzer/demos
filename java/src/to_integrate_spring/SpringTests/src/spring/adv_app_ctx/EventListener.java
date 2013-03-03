package spring.adv_app_ctx;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;

import spring.adv_app_ctx.EventGenerator.SomethingHappened;

public class EventListener implements ApplicationListener {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof SomethingHappened) {
			System.out.println("I found out... something happened to " + event.getSource());
		} else {
			System.out.println(event);
		}
		
	}
	
}
