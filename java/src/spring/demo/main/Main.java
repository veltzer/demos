package spring.demo.main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
	    IMyConfig test = (IMyConfig) factory.getBean("myconfig");
	    BeanFactory realFactory = new ClassPathXmlApplicationContext(test.getConfig());
	    Main.f=realFactory;
	    
	    // here is some application code
	    BeanFactory myFactory=Main.getInstance();
	    IWorker worker=(IWorker)myFactory.getBean("myworker");
	    worker.doWork();
	}
	static private BeanFactory f=null;
	static public synchronized BeanFactory getInstance() {
		return f;
	}
}
