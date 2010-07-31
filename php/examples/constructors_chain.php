<?php

/*
The purpose of this example is to show that the chain
in php works well and that we do not actually need to know
who are parents are and whether their constructors are
implemented or not. Same behaviour is for destructors.
*/

class A {
	public function __construct() {
		echo "<br/>";
		echo "in A constructor";
		echo "<br/>";
	}
}
class B extends A {
	// notice there is no constructor
}
class C extends B {
	public function __construct() {
		parent::__construct();
		echo "<br/>";
		echo "in C constructor";
		echo "<br/>";
	}
}
echo "<br/>creating A<br/>";
// notice the parenthesis in the call (recommended)
$a=new A();
echo "<br/>creating B<br/>";
// look ma, no parenthesis
$b=new B;
echo "<br/>creating C<br/>";
// now back to the sane parenthesis
$c=new C();
?>
