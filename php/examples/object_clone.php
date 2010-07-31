<?php

/*
The purpose of this example is to explain the clone methods behaviour
Notice that __clone method gets called on the cloned object and not on the
original one.
*/

class A {
	public $name="default";
	public function __clone() {
		$this->name="otherval";
		print_r($this);
	}
}
$a=new A();
$b=clone($a);
var_dump($a);
var_dump($b);
echo "val of a is ".$a->name;
echo "<br/>";
echo "val of b is ".$b->name;
echo "<br/>";
?>
