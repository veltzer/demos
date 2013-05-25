package benchmark;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class BenchmarkInterceptor implements MethodInterceptor {
	public Object invoke(MethodInvocation inv) throws Throwable {

		long startTime = System.currentTimeMillis();
		Object result = inv.proceed();
		long endTime = System.currentTimeMillis();
		System.out.println("Time took for: "
				+ inv.getThis().getClass().getName() + " "
				+ inv.getMethod().getName() + " was: " + (endTime - startTime)
				+ " Milliseconds");
		return result;
	}
}
