<?php

/*
 * This is a simple simpletest example...
 *
 * If you want this to run you must install the simpletest extension.
 * You can do that using:
 * - pear
 * - apt-get install php-simpletest
 *
 * How can you use this test ?
 * - Just view it in a browser (it will run the test and show you the result...
 * - From the command line:
 * 	php DemoSimple.php
 */

require_once 'simpletest/unit_tester.php';
require_once 'simpletest/reporter.php';
class SimpleExample extends UnitTestCase {
	function testAddition() {
		$a = 0.7;
		$b = 0.1;
		$c = $a + $b;
		$this->assertEqual($c, 0.8);
	}
}
$test = new SimpleExample();
$test->run(new HtmlReporter());
//$test->run(new TextReporter());
?>
