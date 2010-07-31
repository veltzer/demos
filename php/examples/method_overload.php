<?php
/*
	The purpose is to show that method overloading in php is illegal
*/

class A {
	public function foo($a) {
		echo "<br/>Hello from A::foo, single argument version<br/>";
	}
	// this next function definition will cause a compile time error
	// since no overloading is available (use runtime branching to
	// overload with perhaps varargs methods)
	public function foo($a,$b) {
		echo "<br/>Hello from A::foo, two argument version<br/>";
	}
}
?>
