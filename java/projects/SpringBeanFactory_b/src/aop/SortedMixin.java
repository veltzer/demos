package aop;


import meta.sorter.Sorted;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

@SuppressWarnings("serial")
public class SortedMixin extends DefaultIntroductionAdvisor {

	public SortedMixin() {
		super(new SortedIntroducer(), Sorted.class);
	}
}
