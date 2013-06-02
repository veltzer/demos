package core.nio;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ByteBufferWrapper implements Externalizable {
	private ByteBuffer buffer;

	private static final int BLOCK_SIZE = 1024;

	public ByteBufferWrapper() {
		this(null);
	}

	public ByteBufferWrapper(ByteBuffer ibuffer) {
		buffer = ibuffer;
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		boolean isDirect = in.readBoolean();
		int capacity = in.readInt();
		int limit = in.readInt();
		int position = in.readInt();

		// My decision is to allocate only as 'limit' bytes for this buffer,
		// and not the original capacity.
		if (isDirect) {
			buffer = ByteBuffer.allocateDirect(capacity);
		} else {
			buffer = ByteBuffer.allocate(capacity);
		}
		byte[] bytesBlock = new byte[BLOCK_SIZE];

		while (limit > 0) {
			int readCount = in.read(bytesBlock, 0, Math.min(BLOCK_SIZE, limit));
			buffer.put(bytesBlock, 0, readCount);
			limit -= readCount;
		}
		// in.read(buffer.array());

		buffer.position(position);
		buffer.limit(limit);
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		// We assume we should flip() the buffer. That is, we
		// assume the buffer has been written into.

		final int originalPosition = buffer.position();
		final int originalLimit = buffer.limit();

		out.writeBoolean(buffer.isDirect());
		out.writeInt(buffer.capacity());
		out.writeInt(buffer.limit());
		out.writeInt(buffer.position());
		out.write(buffer.array(), 0, buffer.array().length);

		// How about this code?
		int remaining = buffer.limit();
		out.writeInt(remaining);
		if (remaining > 0) {
			byte[] bytesBlock = new byte[BLOCK_SIZE];
			while (BLOCK_SIZE < remaining) {
				buffer.get(bytesBlock); // certain to succeed
				out.write(bytesBlock);
				remaining -= BLOCK_SIZE;
			}
			buffer.get(bytesBlock, 0, remaining); // certain to succeed
			out.write(bytesBlock);
		}

		// restore position & limit to original value
		buffer.limit(originalLimit);
		buffer.position(originalPosition);
	}

	/**
	 * Get the underlying buffer
	 * @return
	 */
	public ByteBuffer getBuffer() {
		return buffer;
	}

	@Override
	public String toString() {
		return "[ByteBufferWrapper: limit=" + buffer.limit() + "; position="
				+ buffer.position() + "]";
	}

	private static void testSimpleWrite() throws Exception {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		for (int i = 0; i < 26; ++i) {
			buff.put((byte) ('a' + i));
		}
		ByteBufferWrapper wrapper = new ByteBufferWrapper(buff);

		File serialFile = new File("serial.ser");

		ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(serialFile));
		output.writeObject(wrapper);
		output.close();
		System.out.println("Written: " + wrapper.getBuffer());
	}

	private static void testSimpleRead() throws Exception {
		File serialFile = new File("serial.ser");

		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				serialFile));
		ByteBufferWrapper wrapper = (ByteBufferWrapper) input.readObject();
		input.close();

		System.out.println("Read object: " + wrapper);
		byte[] readBytes = wrapper.getBuffer().array();
		for (int i = 0; i < readBytes.length; ++i) {
			System.out.print((char) readBytes[i]);
		}
		System.out.println();
	}

	private static void testCustomWrite() throws Exception {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		for (int i = 0; i < 26; ++i) {
			buff.put((byte) ('a' + i));
		}
		File serialFile = new File("serial.ser");

		ObjectOutputStream output = new BuffersAwareObjectOutputStream(
				new FileOutputStream(serialFile));
		output.writeObject(buff);
		output.close();
	}

	private static void testCustomRead() throws Exception {
		File serialFile = new File("serial.ser");

		ObjectInputStream input = new BuffersAwareObjectInputStream(
				new FileInputStream(serialFile));
		ByteBuffer buff = (ByteBuffer) input.readObject();
		input.close();

		System.out.println("Have read: " + buff);
		byte[] readBytes = buff.array();
		for (int i = 0; i < readBytes.length; ++i) {
			System.out.print((char) readBytes[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("Starting");
		try {
			// simple Object IO streams:
			testSimpleWrite();
			testSimpleRead();

			// custom Object IO streams:
			testCustomWrite();
			testCustomRead();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("Done");
	}
}
