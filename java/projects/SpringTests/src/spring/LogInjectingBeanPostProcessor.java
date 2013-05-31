package spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LogInjectingBeanPostProcessor implements BeanPostProcessor {
	private Logger log;

	public void setLog(Logger ilog) {
		log = ilog;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {

		if (bean instanceof LogAware) {
			((LogAware) bean).setLog(log);
		}

		return bean;
	}

}
