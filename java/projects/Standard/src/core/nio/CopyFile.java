package core.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public abstract class CopyFile {
	public static void main(String[] args) throws Exception {
		boolean fast = true;
		final int size = 1024 * 1024;
		if (args.length < 2) {
			System.err.println("Usage: java CopyFile infile outfile");
			System.exit(1);
		}

		String infile = args[0];
		String outfile = args[1];

		FileInputStream fin = new FileInputStream(infile);
		FileOutputStream fout = new FileOutputStream(outfile);

		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();

		ByteBuffer buffer;
		if (fast) {
			buffer = ByteBuffer.allocateDirect(size);
		} else {
			buffer = ByteBuffer.allocate(size);
		}

		boolean done = false;
		while (!done) {
			int readCount = fcin.read(buffer);
			if (readCount >= 0) {
				buffer.flip();
				fcout.write(buffer);
				buffer.clear();
			} else {
				done = true;
			}
		}
		fin.close();
		fout.close();
		System.out.println("File copy ended.");
	}
}
