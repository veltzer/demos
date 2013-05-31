package programming.samples.interfaces;

public abstract class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Zoo z = new Zoo();
		HPPrinter hpprinter = new HPPrinter();
		z.addAnimal(hpprinter);
	}

}
