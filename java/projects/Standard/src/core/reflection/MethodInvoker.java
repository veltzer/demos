package core.reflection;

import java.lang.reflect.Method;

import javax.swing.JLabel;

public abstract class MethodInvoker {
	/*
	 * An inner class for demo. remember that "static" means it holds no
	 * reference to the outer class or requires it for construction
	 */
	public static class A {
		private String title;

		private Integer id;

		public void setTitle(String ititle) {
			title = ititle;
			System.out.println("Title set to: " + title);
		}

		public String getTitle() {
			return title;
		}

		/**
		 * @return Returns the id.
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * @param id The id to set.
		 */
		public void setId(Integer iid) {
			id = iid;
			System.out.println("id set to: " + id);
		}
	}

	/*
	 * A helper method which relies on the java beans standard and the fact that
	 * bean properties are lower case. This method will convert bean names
	 * (lower case) to the setter method name. So "Name" will give you
	 * "setName". Maybe java already has a class which does this? Need to check.
	 */
	private static String getSetMethodName(String valName) {
		String firstLetter = valName.substring(0, 1).toUpperCase();
		String result = "set" + firstLetter + valName.substring(1);
		return result;
	}

	/*
	 * This is a customized method for strings. It is not strictly required
	 * since the next method which handles objects handles String as well.
	 */
	public static void setObjectValueString(Object o, String valName,
			String newValue) {
		try {
			Class<?> c = o.getClass();
			Method m = c.getMethod(getSetMethodName(valName), String.class);
			m.invoke(o, newValue);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * This method is much more general and can handle any object type Using
	 * auto boxing you can even pass primitives. Isn't life grand?
	 */
	public static void setObjectValue(Object o, String valName, Object newValue) {
		try {
			Class<?> c = o.getClass();
			Method m = c.getMethod(getSetMethodName(valName),
					newValue.getClass());
			m.invoke(o, newValue);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		// a demo of how the getSetMethodName works
		System.out.println("method: " + getSetMethodName("title"));

		A a = new A();
		setObjectValue(a, "title", "kuku");
		// how does this next line work ? Auto boxing anyone ?
		setObjectValue(a, "id", 5);
		System.out.println("title is " + a.getTitle());
		System.out.println("id is " + a.getId());

		// here is a demo with classes that you did not write
		// in this case: JLabel
		JLabel label = new JLabel();
		setObjectValue(label, "text", "this is a label");
		System.out.println(label.getText());

		System.out.println("Done");
	}
}
