package groovy.embed;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyShellExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// call Groovy expressions from Java code
		Binding binding = new Binding();
		binding.setVariable("foo", new Integer(2));
		GroovyShell shell = new GroovyShell(binding);

		Object value = shell.evaluate("println 'Hello World!'; x = 123; return foo * 10");
		assert value.equals(new Integer(20));
		assert binding.getVariable("x").equals(new Integer(123));
	}

}
