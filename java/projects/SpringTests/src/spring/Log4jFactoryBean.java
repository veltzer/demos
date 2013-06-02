package spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;

public class Log4jFactoryBean implements FactoryBean<Object> {
	private String logName;

	public String getLogName() {
		return logName;
	}

	public void setLogName(String ilogName) {
		logName = ilogName;
	}

	@Override
	public Object getObject() throws Exception {
		return Logger.getLogger(getLogName());
	}

	@Override
	public Class<?> getObjectType() {
		return null;
		// return Logger.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
