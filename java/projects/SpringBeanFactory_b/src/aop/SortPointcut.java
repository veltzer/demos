package aop;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import org.springframework.aop.support.StaticMethodMatcher;

public class SortPointcut implements Pointcut {

	public ClassFilter getClassFilter() {
		return new ClassFilter() {
			public boolean matches(Class<?> arg0) {
				return true;
			};
		};
	}

	public MethodMatcher getMethodMatcher() {
		return new StaticMethodMatcher() {

			public boolean matches(Method arg0, Class<?> arg1) {
				if (arg0.getName().equals("sort")) {
					return true;
				}
				return false;
			}

		};
	}

}
