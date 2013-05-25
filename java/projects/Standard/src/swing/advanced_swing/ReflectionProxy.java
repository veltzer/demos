package swing.advanced_swing;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReflectionProxy implements InvocationHandler {
	private Object obj;

	public ReflectionProxy(Object iobj) {
		obj = iobj;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		try {
			return (method.invoke(obj, args));
		} catch (InvocationTargetException err) {
			Throwable e = err.getTargetException();
			CharArrayWriter writer = new CharArrayWriter();
			e.printStackTrace(new PrintWriter(writer));
			JFrame frm = null;
			if (JFrame.getFrames().length > 0) {
				frm = (JFrame) JFrame.getFrames()[0];
			}
			JOptionPane.showMessageDialog(frm, e.getClass().getName() + ": "
					+ e.getMessage() + "\n" + new String(writer.toCharArray()),
					"Exception", JOptionPane.ERROR_MESSAGE);
			throw (e);
		}
	}
}
