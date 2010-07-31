<?php
/*
	The purpose here is to show how final behaves for methods and
	constructors
*/

class A {
	final public function __construct() {
	}
	final public function foo() {
		echo "<br/>";
		var_dump($this);
		echo "<br/>";
	}
}
class B extends A {
	// this next function is a compile error (foo was declared final in A)
	//public function foo() {
	//}
	//the same compilation error will apply on trying to override the constructor
	//public function __construct() {
	//}
}
?>
