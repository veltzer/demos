package groovy.embed;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

public abstract class WithInterface {
	public static void main(String[] args) {
		String fileName = "src/groovy/embed/GroovyImplementation.groovy";
		GroovyClassLoader gcl = new GroovyClassLoader();
		Class<?> clazz;
		try {
			clazz = gcl.parseClass(new File(fileName));
			Object aScript;
			aScript = clazz.newInstance();
			ExampleInterface ifc = (ExampleInterface) aScript;
			System.out.println(ifc.add(5, 5));
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (CompilationFailedException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				gcl.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
