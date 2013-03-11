package org.meta.spring.demo.main;

public class MyConfig implements IMyConfig {
	private String config;

	@Override
	public String getConfig() {
		return config;
	}
	
	public void setConfig(String c) {
		config=c;
	}

}
