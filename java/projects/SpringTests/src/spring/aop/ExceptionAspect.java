package spring.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ExceptionAspect {
	@AfterThrowing(pointcut = "within(spring.MyBean)", throwing = "ex")
	public void log(Throwable ex) throws Throwable {
		System.out.println("oops.... let's do some custom exception handling");
	}
}
