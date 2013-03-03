package spring.javaconfig;

import java.util.Date;

import org.springframework.config.java.annotation.Bean;
import org.springframework.config.java.annotation.Configuration;
import org.springframework.config.java.annotation.ExternalValue;
import org.springframework.config.java.annotation.ResourceBundles;
import org.springframework.config.java.util.DefaultScopes;

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
