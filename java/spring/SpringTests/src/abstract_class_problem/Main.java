package abstract_class_problem;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main {

	public static void main(String[] args) {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("abstract_class_problem/beans.xml"));
		MyAbstractClass mac = (MyAbstractClass)bf.getBean("absTest");
		System.out.println(mac.lookupMethod());
		mac.neverImplemented();
	}

}
