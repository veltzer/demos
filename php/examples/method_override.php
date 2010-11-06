<?php
/*
	The purpse is to show the basic method overriding and parent calling mechanisms
*/

class A {
	public function foo($a) {
		echo "<br/>Hello from A::foo, $a<br/>";
	}
	private function bar($a) {
		echo "<br/>Hello from A::bar, $a<br/>";
	}
	public function callbar($a) {
		//this will call the A::bar method even if the current $this
		//is a child which overrode bar
		$this->bar($a);
	}
}
class B extends A {
	public function foo($a) {
		echo "<br/>Hello from B::foo, $a<br/>";
		parent::foo($a);
	}
	public function bar($a) {
		echo "<br/>Hello from B::bar, $a<br/>";
		// illegal
		//parent::bar($a);
	}
}
$a=new A();
$a->foo(5);
$b=new B();
$b->foo(6);

// this is illegal (bar is private)
// $a->bar(5);
// this is legal (callbar is public)
$a->callbar(5);
$b->bar(6);
?>
