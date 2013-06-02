package spring.component_scan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring/component_scan/beans.xml");
		StuffService ss = (StuffService) ctx.getBean("stuffService");
		ss.doSomethingWithStuff(7);
		((AbstractApplicationContext) ctx).close();
	}
}
