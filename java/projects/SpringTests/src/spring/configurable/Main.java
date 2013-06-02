package spring.configurable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring/configurable/beans.xml");
		System.out.println(new MyDomainObject());
		((AbstractApplicationContext) ctx).close();
	}
}
