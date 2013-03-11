package introducers;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class SummableMixin extends DefaultIntroductionAdvisor {

    public SummableMixin() {
        super(new SummableIntroducer(), Summable.class);
    }
}