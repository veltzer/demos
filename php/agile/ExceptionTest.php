<?php
require_once 'PHPUnit/Framework.php';
 
class ExceptionTest extends PHPUnit_Framework_TestCase {
	/**
	* @expectedException InvalidArgumentException
	*/
	public function testException() {
		throw new InvalidArgumentException();
	}
	public function testException2() {
		$this->setExpectedException('InvalidArgumentException');
		throw new InvalidArgumentException();
	}
	public function testException3() {
		try {
			// ... Code that is expected to raise an exception ...
			throw new InvalidArgumentException();
		}
		catch (InvalidArgumentException $expected) {
			return;
		}
		$this->fail('An expected exception has not been raised.');
	}
}
?>
