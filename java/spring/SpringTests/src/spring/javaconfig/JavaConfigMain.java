package spring.javaconfig;

import org.springframework.config.java.context.JavaConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.MyBean;

public class JavaConfigMain {
	public static void main(String[] args) {
		ApplicationContext ctx1 = new JavaConfigApplicationContext(BeansConfiguration.class);
		MyBean myBean = (MyBean)ctx1.getBean("myBean");
		System.out.println(myBean.getD().getMonth());
		System.out.println(myBean.getX());

		//-------------
		
		ApplicationContext ctx2 = new ClassPathXmlApplicationContext("spring/javaconfig/beans.xml");
		MyBean myBean2 = (MyBean)ctx2.getBean("myBean");
		System.out.println(myBean2.getD().getMonth());
		System.out.println(myBean.getX());
	}
}
