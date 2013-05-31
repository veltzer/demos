package spring.adv_app_ctx;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class EventGenerator implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher iapplicationEventPublisher) {
		applicationEventPublisher = iapplicationEventPublisher;
	}

	public void doSomething() {
		// /...
		applicationEventPublisher.publishEvent(new SomethingHappened(this));
	}

	@SuppressWarnings("serial")
	public static class SomethingHappened extends ApplicationEvent {
		public SomethingHappened(Object source) {
			super(source);
		}
	}
}
