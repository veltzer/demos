package core.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * This class demos the use of a proxy. It intercepts all calls to an interface
 * (IPerson) using an Invocation Handler.
 * @author Mark Veltzer <mark@veltzer.net>
 */

public class PersonProxy {

	static public interface IPerson {
		public void setName(String name);

		public String getName();

		public void setAge(int age);

		public int getAge();
	}

	static public class Person implements IPerson {
		private String name;
		private int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	/**
	 * This invocation handler does nothing special but print the calls to it.
	 * It does not route the calls to any other object nor does it return any
	 * sane values (always returns null).
	 * @author Mark Veltzer <mark@veltzer.net>
	 */
	static public class PrintingInvocationHandler implements InvocationHandler {

		public Object invoke(Object proxy, Method method, Object[] args) {
			System.out.println("Object is " + proxy);
			System.out.println("Method is " + method.getName());
			System.out.println("Args are " + args);
			return null;
		}
	}

	/**
	 * The next invocation handler routes all calls to another object and does
	 * nothing special besides that. It does print stuff to show that it is
	 * working
	 */
	static public class RoutingInvocationHandler implements InvocationHandler {
		private Object obj;

		public RoutingInvocationHandler(Object obj) {
			this.obj = obj;
		}

		public Object invoke(Object proxy, Method method, Object[] args) {
			System.out.println("Object is " + proxy);
			System.out.println("Method is " + method.getName());
			System.out.println("Args are " + args);
			try {
				return method.invoke(obj, args);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * This next invocation handler synchronized the entire method set of an
	 * object. Notice that we synchronized on the proxy object and not on the
	 * object itself although that is an option too.
	 * @author Mark Veltzer <mark@veltzer.net>
	 */
	static public class SynchronizedInvocationHandler implements
			InvocationHandler {
		private Object obj;

		public SynchronizedInvocationHandler(Object obj) {
			this.obj = obj;
		}

		public Object invoke(Object proxy, Method method, Object[] args) {
			synchronized (proxy) {
				try {
					return method.invoke(obj, args);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * This is a method similar to Collections.synchronize
	 * @param o the object to wrap
	 * @return the proxy object
	 * @author Mark Veltzer <mark@veltzer.net>
	 */

	static public Object syncIt(Object o) {
		Class<?>[] interfaces = {
			o.getClass()
		};
		SynchronizedInvocationHandler sih = new SynchronizedInvocationHandler(o);
		Object proxy = Proxy.newProxyInstance(o.getClass().getClassLoader(),
				interfaces, sih);
		return proxy;
	}

	/**
	 * This next invocation handler is for any Object. interface. It blocks
	 * method names which start with "set" and allows all others.
	 * @author Mark Veltzer <mark@veltzer.net>
	 */
	static public class ReadOnlyInvocationHandler implements InvocationHandler {
		private Object obj;

		public ReadOnlyInvocationHandler(Object obj) {
			this.obj = obj;
		}

		private static final String ERR_STRING1 = "Cannot call set";

		public Object invoke(Object proxy, Method method, Object[] args) {
			if (method.getName().startsWith("set")) {
				throw new RuntimeException(ERR_STRING1);
			}
			try {
				return method.invoke(obj, args);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class<?>[] interfaces = {
			IPerson.class
		};
		Person p = new Person();
		p.setName("mark");
		ReadOnlyInvocationHandler pih = new ReadOnlyInvocationHandler(p);
		Object o = Proxy.newProxyInstance(pih.getClass().getClassLoader(),
				interfaces, pih);
		System.out.println("Class is " + o.getClass().getName());
		// Here comes the magic (this next cast will NOT throw a
		// ClassCastExecption
		IPerson ip = (IPerson) o;
		try {
			ip.setName("hh");
		} catch (RuntimeException e) {
			System.out.println("yes, got exception for calling set");
		}
		System.out.println("person name is " + ip.getName());

		/* Demonstration of the synchronization interface */
		List<Integer> li = new ArrayList<Integer>();
		li.add(5);
		li.add(6);
		@SuppressWarnings("unchecked")
		List<Integer> sli = (List<Integer>) syncIt(li);
		// now you can do multi threaded work with this list
		System.out.println("the element is " + sli.get(0));
	}
}
