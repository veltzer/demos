package groovy.embed;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

public class WithClassLoader {
	public static void main(String args[]) {
		ClassLoader parent = WithClassLoader.class.getClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);
		Class<?> groovyClass;
		try {
			groovyClass = loader.parseClass(new File("HelloWorld.groovy"));
		} catch (CompilationFailedException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// let's call some method on an instance
		GroovyObject groovyObject;
		try {
			groovyObject = (GroovyObject) groovyClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);			
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Object[] groovy_args = {};
		groovyObject.invokeMethod("run", groovy_args);
	}
}
