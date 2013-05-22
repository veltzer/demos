package aop;

import interbit.sorter.Sorted;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class SortedMixin extends DefaultIntroductionAdvisor {

    public SortedMixin() {
        super(new SortedIntroducer(), Sorted.class);
    }
}