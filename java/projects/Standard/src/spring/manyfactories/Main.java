package spring.manyfactories;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"beans.xml", Main.class);
		IMyConfig test = (IMyConfig) factory.getBean("myconfig");
		ApplicationContext realFactory = new ClassPathXmlApplicationContext(
				test.getConfig(), Main.class);
		Main.f = realFactory;

		// here is some application code
		BeanFactory myFactory = Main.getInstance();
		IWorker worker = (IWorker) myFactory.getBean("myworker");
		worker.doWork();
		((AbstractApplicationContext) factory).close();
	}

	static private ApplicationContext f = null;

	static public synchronized ApplicationContext getInstance() {
		return f;
	}
}
