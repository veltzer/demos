package spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		BeanFactory bf = new ClassPathXmlApplicationContext("beans.xml");
		
		MyBean b = (MyBean)bf.getBean("myBean");
		System.out.println(b.getD());
		System.out.println(b.getX());

	}

}
