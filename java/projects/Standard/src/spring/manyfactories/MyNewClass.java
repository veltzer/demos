package spring.manyfactories;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class MyNewClass implements BeanFactoryAware {

	@Override
	public void setBeanFactory(BeanFactory factory) {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
