package spring.testing;

public class AnImportantDependencyImpl implements AnImportantDependency {
	public int doSomethingImportant() {
		return (int) (Math.random() * 20 - 10);
	}
}
