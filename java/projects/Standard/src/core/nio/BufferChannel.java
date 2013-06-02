package core.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public abstract class BufferChannel {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			ByteBuffer buff = ByteBuffer.allocate(1024);
			for (int i = 0; i < 26; ++i) {
				buff.put((byte) ('a' + i));
			}
			System.out.println("pos is: " + buff.position());
			buff.put((byte) ('a'));
			System.out.println("pos is: " + buff.position());
			buff.put(304, (byte) ('a'));
			System.out.println("pos is: " + buff.position());

			FileOutputStream output = new FileOutputStream("buff.txt");
			FileChannel channel = output.getChannel();
			buff.flip();
			channel.write(buff);
			output.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("Done");
	}

}
