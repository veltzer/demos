package spring;

import java.util.Date;

import org.springframework.beans.factory.FactoryBean;

public class TestFactoryBean implements FactoryBean<Object> {
	private Date d = new Date();

	@Override
	public Object getObject() throws Exception {
		System.out.println(new Date());
		return d;
	}

	@Override
	public Class<Date> getObjectType() {
		return Date.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
