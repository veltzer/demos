package spring.manyfactories;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class MyNewClass implements BeanFactoryAware {

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
