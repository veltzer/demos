<?php
require_once 'PHPUnit/Framework.php';

class DatabaseTest extends PHPUnit_Framework_TestCase {
	protected function setUp() {
		if (!extension_loaded('foobarDatabaseExtension')) {
			$this->markTestSkipped(
				'The MySQLi extension is not available.'
			);
		}
	}

	public function testConnection() {
		// code to do more testing here...
		$this->assertTrue(False);
	}
}
?>
