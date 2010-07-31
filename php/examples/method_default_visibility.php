<?php
/*
	The purpose is to show how the default function visibility behaves
*/

class A {
	function foo() {
		echo "<br/>";
		var_dump($this);
		echo "<br/>";
	}
}
class B extends A {
	public function bar() {
		$this->foo();
	}
}
$a=new A();
//this next line is NOT an error (backwards compatibility)
$a->foo();
//this next line generates a B type object which can also
//call foo (that means that child access is allowed)
$b=new B();
$b->bar();
?>
