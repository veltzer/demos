package spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class MultiXmlMain {

	public static void main(String[] args) {
		BeanFactory bf = new ClassPathXmlApplicationContext(new String[] {
				"beans3.xml", "beans2.xml"
		});
		System.out.println(bf.getBean("x2"));
		((AbstractApplicationContext) bf).close();
	}

}
