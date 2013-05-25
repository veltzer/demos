package aop;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

import sorter.Sorted;

@SuppressWarnings("serial")
public class SortedMixin extends DefaultIntroductionAdvisor {

	public SortedMixin() {
		super(new SortedIntroducer(), Sorted.class);
	}
}
