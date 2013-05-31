package spring.adv_app_ctx;

import java.io.IOException;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

public class ResourceUsingService implements ResourceLoaderAware {
	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader iresourceLoader) {
		resourceLoader = iresourceLoader;
	}

	public void doSomething() {
		Resource r = resourceLoader
				.getResource("classpath:spring/adv_app_ctx/messages.properties");

		try {
			FileCopyUtils.copy(r.getInputStream(), System.out);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
