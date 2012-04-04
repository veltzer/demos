package core.nio;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class BuffersAwareObjectOutputStream extends ObjectOutputStream {
	public BuffersAwareObjectOutputStream(OutputStream out) throws IOException {
		super(out);
		this.enableReplaceObject(true);
	}

	@Override
	protected Object replaceObject(Object obj) throws IOException {
		if (obj.getClass() == ByteBuffer.class) {
			ByteBuffer buffer = (ByteBuffer) obj;
			return new ByteBufferWrapper(buffer);
		}
		return super.replaceObject(obj);
	}

}
