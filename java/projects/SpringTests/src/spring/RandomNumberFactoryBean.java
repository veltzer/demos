package spring;

import org.springframework.beans.factory.FactoryBean;

public class RandomNumberFactoryBean implements FactoryBean<Object> {
	@Override
	public Object getObject() throws Exception {
		return Math.random();
	}

	@Override
	public Class<Double> getObjectType() {
		return Double.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
