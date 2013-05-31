package core.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public abstract class FileMapDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			RandomAccessFile f = new RandomAccessFile(
					new File("/temp/buff.txt"), "rw");
			FileChannel c = f.getChannel();
			MappedByteBuffer b = c.map(FileChannel.MapMode.READ_WRITE, 0,
					c.size());
			b.put((byte) 'O');
			b.putLong(6, -12L);
			c.close();
			f.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("Done");
	}

}
