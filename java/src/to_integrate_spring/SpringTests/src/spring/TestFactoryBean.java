package spring;

import java.util.Date;

import org.springframework.beans.factory.FactoryBean;

public class TestFactoryBean implements FactoryBean {
	private Date d = new Date();
	
	@Override
	public Object getObject() throws Exception {
		System.out.println(new Date());
		return d;
	}

	@Override
	public Class getObjectType() {
		return Date.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
