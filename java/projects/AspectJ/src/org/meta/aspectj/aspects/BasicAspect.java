package org.meta.aspectj.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BasicAspect {

	/*
	 * Read as -- do this *before* any *call* to the function
	 * *java.io.PrintStream.println* that takes a *String* and returns *void*
	 * and the function is not called within any class under the package
	 * net.andrewewhite.aspects
	 */
	/*
	 * @Before("call(void java.io.PrintStream.println(String)) " +
	 * "&& !within(org.meta.aspectj.aspects..*)") public void
	 * beforePrintlnCall(JoinPoint jp) {
	 * System.out.println("About to make call to print Hello World "+jp); }
	 * @After("call(void java.io.PrintStream.println(String)) " +
	 * "&& !within(org.meta.aspectj.aspects..*)") public void
	 * afterPrintlnCall(JoinPoint jp) {
	 * System.out.println("Just made call to print Hello World "+jp); }
	 */
	/*
	 * @Before("call(* *.*(..)) && !within(org.meta.aspectj.aspects..*)") public
	 * void beforeAdd(JoinPoint jp) { System.out.println("before jp is "+jp); }
	 * @After("call(* *.*(..)) && !within(org.meta.aspectj.aspects..*)") public
	 * void afterAdd(JoinPoint jp) { System.out.println("after jp is "+jp); }
	 */
	private static boolean debug = true;

	@Before("call(* org.meta.aspectj.Calc.add(int,int)) && args(a,b)")
	public void beforeAdd(int a, int b) {
		if (debug) {
			System.out.println("a is " + a);
			System.out.println("b is " + b);
		}
	}

}
