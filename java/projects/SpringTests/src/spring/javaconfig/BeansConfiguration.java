package spring.javaconfig;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MyBean;

@Configuration
@ResourceBundles("classpath:config")
public abstract class BeansConfiguration {
	@Bean
	public MyBean myBean() {
		MyBean myBean = new MyBean();
		myBean.setD(x2());
		myBean.setX(importantNumber());
		return myBean;
	}
	
	@Bean(scope=DefaultScopes.PROTOTYPE)
	public Date x2() {
		return new Date();
	}
	
	@ExternalValue
	public abstract int importantNumber();
}
