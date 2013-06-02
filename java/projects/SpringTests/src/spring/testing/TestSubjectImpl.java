package spring.testing;

public class TestSubjectImpl implements TestSubject {
	private AnImportantDependency anImportantDependency;

	public AnImportantDependency getAnImportantDependency() {
		return anImportantDependency;
	}

	public void setAnImportantDependency(
			AnImportantDependency ianImportantDependency) {
		anImportantDependency = ianImportantDependency;
	}

	public int checkNumber(int a) {
		if (a == 0) {
			throw new IllegalArgumentException();
		} else if (a < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	public int getAndCheckNumber() {
		int a = anImportantDependency.doSomethingImportant();
		return checkNumber(a);
	}
}
