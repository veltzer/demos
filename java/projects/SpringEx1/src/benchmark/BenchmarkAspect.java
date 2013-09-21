package benchmark;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class BenchmarkAspect {
	@Around("execution(* meta.sorter.Sorter.*(..))")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		long endTime = System.currentTimeMillis();
		System.out.println("Time took for: "
				+ pjp.getSignature().toLongString() + " was: "
				+ (endTime - startTime) + " Milliseconds.");
		return result;
	}
}
