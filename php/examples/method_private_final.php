<?php
/*
	The purpose is to show that:
	1. Child cannot call private method of parent
	2. Child can overiride private methods of parent
*/

class A {
	private function foo() {
		echo "<br/>hello from A::foo<br/>";
	}
	private function another() {
		echo "<br/>hello from A::another<br/>";
	}
	public function callanother() {
		//this calls the A implementation, even if the object
		//is a child and overrode it
		$this->another();
	}
	final private function finalfoo() {
		echo "<br/>hello from A::finalfoo<br/>";
	}
}
class B extends A {
	// this next function is a compile error (foo was declared final in A)
	//public function foo() {
	//}
	public function trytocallprivate() {
		// this is a runtime error - cannot call a private method
		// this is not cought at compile time!!!
		$this->foo();
	}
	public function another() {
		echo "<br/>hello from B::another<br/>";
		//next line is illegal but only cought at runtime
		//parent::another();
	}
}
$b=new B();
// this will cause the runtime error
// $b->trytocallprivate();
$b->another();
$b->callanother();
?>
