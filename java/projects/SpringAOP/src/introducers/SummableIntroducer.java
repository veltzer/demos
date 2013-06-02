package introducers;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

@SuppressWarnings("serial")
public class SummableIntroducer extends DelegatingIntroductionInterceptor
		implements Summable {
	private long count = 0;

	public long getSum() {
		return count;
	}

	@Override
	public Object invoke(MethodInvocation inv) throws Throwable {
		if (!inv.getMethod().getName().equals("getSum")) {
			count++;
		}
		return super.invoke(inv);
	}
}
