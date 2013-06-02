package test;

import introducers.Summable;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.ListHolder;

public abstract class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		BeanPostProcessor advisor = (BeanPostProcessor) context
				.getBean("authoproxier");
		((ConfigurableBeanFactory) context).addBeanPostProcessor(advisor);
		ListHolder lh = (ListHolder) context.getBean("ListHolderArray");
		lh.iterate();
		System.out.println(((Summable) lh).getSum());
		lh = (ListHolder) context.getBean("ListHolderLinkedList");
		lh.iterate();
		lh.iterate();
		lh.iterate();
		lh.iterate();
		System.out.println(((Summable) lh).getSum());
		System.out.println(((Summable) lh).getSum());
		((AbstractApplicationContext) context).close();
	}

}
