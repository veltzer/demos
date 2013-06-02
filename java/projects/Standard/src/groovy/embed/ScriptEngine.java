package groovy.embed;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;

public abstract class ScriptEngine {

	public static void main(String[] args) {

		String[] roots = new String[] {
			"src/groovy/embed"
		};
		GroovyScriptEngine gse;
		try {
			gse = new GroovyScriptEngine(roots);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Binding binding = new Binding();
		binding.setVariable("input", "world");
		try {
			gse.run("hello.groovy", binding);
		} catch (ResourceException e) {
			throw new RuntimeException(e);
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
		System.out.println(binding.getVariable("output"));

	}
}
