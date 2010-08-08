<?php
require_once 'PHPUnit/Framework.php';

class DependencyTest extends PHPUnit_Framework_TestCase
{
	public function testOne() {
		$this->assertTrue(TRUE);
		//$this->assertTrue(FALSE);
	}
	     
	/**
	 * @depends testOne
	 */
	public function testTwo() {
	}
}
?>
