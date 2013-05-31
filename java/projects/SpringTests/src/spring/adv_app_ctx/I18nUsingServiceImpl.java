package spring.adv_app_ctx;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class I18nUsingServiceImpl implements MessageSourceAware,
		I18nUsingService {
	private MessageSource messageSource;
	private Locale locale = Locale.getDefault();

	@Override
	public void setMessageSource(MessageSource imessageSource) {
		messageSource = imessageSource;
	}

	/*
	 * (non-Javadoc)
	 * @see spring.adv_app_ctx.I18nUsingService#doSomething()
	 */
	public void doSomething() {
		System.out.println(messageSource.getMessage("msg1", new Object[] {
			"Ido"
		}, locale));
	}

	public void setLocale(Locale ilocale) {
		locale = ilocale;
	}
}
