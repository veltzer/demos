package spring.adv_app_ctx;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AdvancedAppContext {
	public static void main(String[] args) {
		BeanFactory bf = new ClassPathXmlApplicationContext(
				"spring/adv_app_ctx/beans.xml");

		// ((I18nUsingService) bf.getBean("i18nUsingService")).doSomething();
		//
		// System.out.println("\n------------------------\n");
		//
		((EventGenerator) bf.getBean("eventGenerator")).doSomething();
		//
		// System.out.println("\n------------------------\n");
		//
		// ((ResourceUsingService)
		// bf.getBean("resourceUsingService")).doSomething();
		//
		((AbstractApplicationContext) bf).close();
	}
}
