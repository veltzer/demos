package test;

import introducers.Summable;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import domain.ListHolder;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Resource res = new FileSystemResource("beans.xml");
		ConfigurableBeanFactory bf = new XmlBeanFactory(res);
		BeanPostProcessor advisor = (BeanPostProcessor) bf
				.getBean("authoproxier");
		bf.addBeanPostProcessor(advisor);
		ListHolder lh = (ListHolder) bf.getBean("ListHolderArray");
		lh.iterate();
		System.out.println(((Summable) lh).getSum());
		lh = (ListHolder) bf.getBean("ListHolderLinkedList");
		lh.iterate();
		lh.iterate();
		lh.iterate();
		lh.iterate();
		System.out.println(((Summable) lh).getSum());
		System.out.println(((Summable) lh).getSum());
	}

}
