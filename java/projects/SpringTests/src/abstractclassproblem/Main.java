package abstractclassproblem;

import java.io.PrintStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class Main {

	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("beans.xml");
		MyAbstractClass mac = (MyAbstractClass) bf.getBean("absTest");
		System.out.println(mac.lookupMethod());
		mac.neverImplemented();
		((PrintStream) bf).close();
	}

}
