package spring.aop;

import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.MyBean;

public abstract class AspectMain {

	public static void main(String[] args) {
		BeanFactory bf = new ClassPathXmlApplicationContext("beans.xml");
		Object bean = bf.getBean("myBean");
		MyBean b = (MyBean) bean;
		b.getD();
		b.setX(4);
		b.setD(new Date());

		UsageTracked ut = (UsageTracked) b;
		System.out.println(ut.getCount() + " methods were invoked on " + ut);
		((AbstractApplicationContext) bf).close();
	}

}
