package introducers;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

@SuppressWarnings("serial")
public class SummableMixin extends DefaultIntroductionAdvisor {

	public SummableMixin() {
		super(new SummableIntroducer(), Summable.class);
	}
}
