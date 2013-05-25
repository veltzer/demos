package spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class UsageTrackingAspect {
	@DeclareParents(value = "spring.MyBean", defaultImpl = DefaultUsageTracked.class)
	private static UsageTracked mixin;

	// -------------------------

	@Before("within(spring.MyBean) && this(usageTracked)")
	public void recordUsage(UsageTracked usageTracked) {
		usageTracked.inc();
	}
}
