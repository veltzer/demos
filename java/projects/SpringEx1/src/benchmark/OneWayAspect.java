package benchmark;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class OneWayAspect {
	@Around("execution(* meta.sorter.Sorter.sort(..))")
	public Object invoke(final ProceedingJoinPoint pjp) throws Throwable {

		Thread oneWay = new Thread(new Runnable() {
			public void run() {
				try {
					pjp.proceed();
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			};
		});
		oneWay.start();
		return null;
	}
}
