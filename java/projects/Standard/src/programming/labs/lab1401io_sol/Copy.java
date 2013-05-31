package programming.labs.lab1401io_sol;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class Copy {
	private static final int BUF_SIZE = 1024;

	public static void copy(String inName, String outName) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
			in = new BufferedInputStream(new FileInputStream(inName));
			out = new BufferedOutputStream(new FileOutputStream(outName));
			// notice that we use our own buffer anyway
			// even though we use the buffer steams in order
			// to cut down on the number of method calls
			// (compare to reading and writing a single byte
			// at a time)
			byte[] buf = new byte[BUF_SIZE];
			int r;
			while ((r = in.read(buf)) > 0) {
				out.write(buf, 0, r);
			}
		} catch (IOException e) {
			// notice the conversion to runtime exception so
			// as not the sweep the error condition under the
			// table
			throw new RuntimeException(e);
		} finally {
			// notice the strict way to make sure we
			// close both files
			if (in != null) {
				try {
					in.close();
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
		}
	}

	private static final String ERR_STRING1 = "Copy operation seems to have failed";

	public static void main(String[] args) {
		// notice the use of relative file names
		// using absolute file names is from the devil
		final String original = "origin.txt";
		final String copy = "copy.txt";
		// delete the old version
		// strictly speaking, this is not a must since opening a file
		// for writing also truncates it to size 0 and does not cause
		// an exception
		File f = new File(copy);
		if (f.exists()) {
			f.delete();
		}
		// Here we call the copy method
		Copy.copy(original, copy);
		// Make sure that the copy file exists and throw
		// an exception if it does not
		if (!f.exists()) {
			throw new RuntimeException(ERR_STRING1);
		}
	}
}
