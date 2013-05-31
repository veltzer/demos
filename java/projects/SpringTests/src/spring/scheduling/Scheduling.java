package spring.scheduling;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class Scheduling {
	public static void main(String[] args) throws Exception {
		BeanFactory bf = new ClassPathXmlApplicationContext(
				"spring/scheduling/beans.xml");

		Thread.sleep(20000);
		((AbstractApplicationContext) bf).close();
	}
}
