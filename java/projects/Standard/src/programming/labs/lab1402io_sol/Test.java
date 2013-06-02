package programming.labs.lab1402io_sol;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Test {

	// notice that the method does not require serializable
	// to be passed. The check for serializable implementation
	// takes place at runtime and not at compile time
	public static void writeObject(Object o, String filename) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(filename)));
			out.writeObject(o);

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	// return is not serializable but rather object
	// see note above
	public static Object readObject(String filename) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(filename)));
			Object o = in.readObject();
			return o;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

	}

	public static void main(String[] args) {
		final String filename = "bank.ser";
		Business bank = Bank.getBank();
		bank.addCustomer(new Customer("Moshe", "1234", 20));

		// delete file from last time
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
		}
		writeObject(bank, filename);
		// explicit line to set bank to null
		bank = null;
		// explicitly remove customer 0 from the original bank
		Bank.getBank().removeCustomer(0);
		bank = (Bank) readObject(filename);
		System.out.println(bank.getCustomer(0).getName());
	}
}
