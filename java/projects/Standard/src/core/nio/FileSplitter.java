package core.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public abstract class FileSplitter {

	public static void split(FileChannel fileIn, int size,
			FileChannel fileOut1, FileChannel fileOut2) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(size);

		while (true) {
			int readen = fileIn.read(buffer);
			buffer.flip();
			fileOut1.write(buffer);
			if (readen < size) {
				break;
			}
			buffer.clear();

			readen = fileIn.read(buffer);
			buffer.flip();
			fileOut2.write(buffer);
			if (readen < size) {
				break;
			}
			buffer.clear();
		}
	}

}
