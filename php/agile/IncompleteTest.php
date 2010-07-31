<?php
require_once 'PHPUnit/Framework.php';

class IncompleteTest extends PHPUnit_Framework_TestCase {
	public function testSomething() {
		// Optional: Test anything here, if you want.
		$this->assertTrue(TRUE, 'This should already work.');
		// Stop here and mark this test as incomplete.
		$this->markTestIncomplete(
			'This test has not been implemented yet.'
		);
	}
}
?>
