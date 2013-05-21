package core.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class BuffersAwareObjectInputStream extends ObjectInputStream {
	public BuffersAwareObjectInputStream(InputStream in) throws IOException {
		super(in);
		this.enableResolveObject(true);
	}

	@Override
	protected Object resolveObject(Object obj) throws IOException {
		if (obj instanceof ByteBufferWrapper) {
			ByteBufferWrapper wrapper = (ByteBufferWrapper) obj;
			return wrapper.getBuffer();
		}
		return super.resolveObject(obj);
	}

}
