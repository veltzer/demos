package spring;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

public class MyBean implements BeanFactoryAware, BeanNameAware, LogAware {
	private String beanName;

	public String getBeanName() {
		return beanName;
	}

	private BeanFactory beanFactory;

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	private String name;
	private Class<?> c;
	private int x;
	private Date d;
	private List<Object> stuff;
	private Logger logger;

	@Override
	public void setLog(Logger ilogger) {
		setLogger(ilogger);
	}

	public Class<?> getC() {
		return c;
	}

	public void setC(Class<?> ic) {
		c = ic;
	}

	public List<Object> getStuff() {
		return stuff;
	}

	public void setStuff(List<Object> istuff) {
		stuff = istuff;
	}

	public void setX(int ix) {
		x = ix;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date id) {
		d = id;
	}

	private static final String ERR_STRING1 = "negative value";

	public void failOnNegative(int i) {
		if (i < 0) {
			throw new RuntimeException(ERR_STRING1);
		}
	}

	public int getX() {
		return x;
	}

	public void setBeanFactory(BeanFactory ibeanFactory) {
		beanFactory = ibeanFactory;
		System.out.println(beanFactory);
	}

	public void setBeanName(String ibeanName) {
		beanName = ibeanName;
	}

	public String getName() {
		return name;
	}

	public void setName(String iname) {
		name = iname;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger ilogger) {
		logger = ilogger;
	}
}
