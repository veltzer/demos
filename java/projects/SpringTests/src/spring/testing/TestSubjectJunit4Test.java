package spring.testing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestSubjectJunit4Test {
	private TestSubject testSubject;

	@Autowired
	public void setTestSubject(TestSubject itestSubject) {
		testSubject = itestSubject;
	}

	// ----------------- TESTS -------------------

	@Test
	public void checkNumberWithPositiveNumber() {
		assertTrue("Positive values should return 1",
				testSubject.checkNumber(12) == 1);
	}

	@Test
	public void checkNumberWithNegativeNumber() {
		assertTrue("Negative values should return -1",
				testSubject.checkNumber(-2) == -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void checkNumberWithZero() {
		testSubject.checkNumber(0);
	}

	// ---------

	@Test
	public void getAndCheckNumber() {
		int a = testSubject.getAndCheckNumber();

		System.out.println(a);
		// ?!??!?!?!
	}

}
