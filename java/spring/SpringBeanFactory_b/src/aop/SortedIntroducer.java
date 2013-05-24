package aop;


import interbit.sorter.Sorted;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class SortedIntroducer extends DelegatingIntroductionInterceptor  implements Sorted{
	private boolean sorted = false;
	
	@Override
	public Object invoke(MethodInvocation inv) throws Throwable {
		if (inv.getMethod().getName().equals("sort"))
			sorted = true;
		return super.invoke(inv);
	}
	public boolean isSorted() {
		return sorted;
	}
	

	

}
