package spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {
	@Around("within(spring.MyBean)")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("before " + pjp.toShortString());
		Object r = pjp.proceed();
		System.out.println("after " + pjp.toShortString());
		return r;
	}
}
