package spring;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;


public class MyBean implements BeanFactoryAware, BeanNameAware, LogAware
{
	private String beanName;
	private BeanFactory beanFactory;
	private String name;
	private Class c;
	private int x;
	private Date d;
	private List stuff;
	private Logger logger ;
	
	@Override
	public void setLog(Logger logger) {
		this.logger = logger;
	}



	public Class getC() {
		return c;
	}



	public void setC(Class c) {
		this.c = c;
	}



	public List getStuff() {
		return stuff;
	}
	
	public void setStuff(List stuff) {
		this.stuff = stuff;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	
	public void failOnNegative(int i) {
		if (i < 0) throw new RuntimeException(i + " is negative !!!");
	}



	public int getX() {
		return x;
	}



	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
		System.out.println(beanFactory);
	}



	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
}
