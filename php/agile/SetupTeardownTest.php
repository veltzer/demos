<?php
require_once 'PHPUnit/Framework.php';

/*
 * This is a setUp/tearDown demo.
 * The setUp function will be called before executing each test in this test case.
 * The tearDown function will be called after executing each test in this test case.
 * Notice that the test cases in this demo are NOT grouped which means that an each
 * individual test function is going to trigger a setUp before and tearDown after.
 * To avoid this, group your test functions into groups using the relevant annotations.
 *
 * To see the trace, run this on the command line:
 * phpunit SetupTest.php  
 */

class SetupTeardownTest extends PHPUnit_Framework_TestCase {
	protected $fixture;

	protected function setUp() {
		echo "in setDown\n";
		$this->fixture=Array(); // Create the Array fixture.
	}

	public function testNewArrayIsEmpty() {
		echo "in testNewArrayIsEmpty\n";
		// Assert that the size of the Array fixture is 0.
		$this->assertEquals(0,sizeof($this->fixture));
	}

	public function testArrayContainsAnElement() {
		echo "in testArrayContainsAnElement\n";
		$this->fixture[]='Element'; // Add an element to the Array fixture.
		// Assert that the size of the Array fixture is 1.
		$this->assertEquals(1,sizeof($this->fixture));
	}

	protected function tearDown() {
		echo "in tearDown\n";
		$this->fixture = null;
	}
}
?>
