package aop;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

import sorter.Sorted;

public class SortedMixin extends DefaultIntroductionAdvisor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SortedMixin() {
		super(new SortedIntroducer(), Sorted.class);
	}
}
