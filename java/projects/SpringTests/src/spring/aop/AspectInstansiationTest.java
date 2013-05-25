package spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect("perthis(within(spring.MyBean))")
public class AspectInstansiationTest {
	@Around("within(spring.MyBean)")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("instance " + hashCode());
		Object r = pjp.proceed();
		return r;
	}
}
