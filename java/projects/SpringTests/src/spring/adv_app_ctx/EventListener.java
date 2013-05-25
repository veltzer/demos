package spring.adv_app_ctx;

import org.springframework.context.ApplicationListener;

import spring.adv_app_ctx.EventGenerator.SomethingHappened;

public class EventListener implements ApplicationListener<SomethingHappened> {

	@Override
	public void onApplicationEvent(SomethingHappened event) {
		System.out.println("I found out... something happened to "
				+ event.getSource());
	}
}
