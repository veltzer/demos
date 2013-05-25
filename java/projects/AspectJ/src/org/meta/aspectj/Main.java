package org.meta.aspectj;

public abstract class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calc calc = new Calc();
		System.out.println("did you know that 2+2 is " + calc.add(2, 2));
	}

}
