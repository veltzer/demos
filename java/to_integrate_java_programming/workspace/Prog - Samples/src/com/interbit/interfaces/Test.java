package com.interbit.interfaces;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Zoo z = new Zoo();
		HPPrinter hpprinter = new HPPrinter();
		z.addAnimal(hpprinter);
	}

}
