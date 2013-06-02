package core.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
public abstract class Split {

	/**
	 * @param args
	 * @throws IOException
	 */
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileInputStream fileInStream = new FileInputStream(args[0]);
		FileOutputStream fileOutputStream1 = new FileOutputStream(args[2]);
		FileOutputStream fileOutputStream2 = new FileOutputStream(args[3]);
		int size = Integer.parseInt(args[1]);
		FileChannel fileIn = fileInStream.getChannel();
		FileChannel fileOut1 = fileOutputStream1.getChannel();
		FileChannel fileOut2 = fileOutputStream2.getChannel();
		FileSplitter.split(fileIn, size, fileOut1, fileOut2);
		fileInStream.close();
		fileOutputStream1.close();
		fileOutputStream2.close();
	}
}
