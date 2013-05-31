package core.instrument;

import java.util.Date;

public abstract class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("starting");
		Date d = null;
		System.out.println("after declaration");
		d = new Date();
		String s = d.toString();
		System.out.println("after instantiations: " + s);
		System.out.println("Done");
	}

}
