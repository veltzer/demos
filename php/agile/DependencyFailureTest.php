<?php
require_once 'PHPUnit/Framework.php';

class DependencyFailureTest extends PHPUnit_Framework_TestCase
{
	public function testOne() {
		$this->assertTrue(TRUE);
	}
	     
	/**
	 * @depends testOne
	 */
	public function testTwo() {
	}
}
?>
