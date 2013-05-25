package spring.testing;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

@SuppressWarnings("deprecation")
public class TestSubjectTestCase extends
		AbstractDependencyInjectionSpringContextTests {
	private TestSubject testSubject;

	public void setTestSubject(TestSubject testSubject) {
		this.testSubject = testSubject;
	}

	@Override
	protected String getConfigPath() {
		return getClass().getSimpleName() + "-context.xml";
	}

	// ----------------- TESTS -------------------

	public void testCheckNumberWithPositiveNumber() {
		assertTrue("Positive values should return 1",
				testSubject.checkNumber(12) == 1);
	}

	public void testCheckNumberWithNegativeNumber() {
		assertTrue("Negative values should return -1",
				testSubject.checkNumber(-2) == -1);
	}

	public void testCheckNumberWithZero() {
		int value = 0;
		try {
			value = testSubject.checkNumber(0);
			fail("Expected failure, instead got " + value);
		} catch (IllegalArgumentException iae) {
			// This is good !
		}
	}

	// ---------

	public void testgetAndCheckNumber() {
		int a = testSubject.getAndCheckNumber();

		System.out.println(a);
		// ?!??!?!?!
	}

}
