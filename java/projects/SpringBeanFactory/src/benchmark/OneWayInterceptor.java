package benchmark;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class OneWayInterceptor implements MethodInterceptor {
	public Object invoke(final MethodInvocation inv) throws Throwable {

		Thread oneWay = new Thread(new Runnable() {
			public void run() {
				try {
					inv.proceed();
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			};
		});
		oneWay.start();
		return null;
	}
}
