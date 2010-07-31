<?php

/*
	The object of this is to show what happens when we use
	both a variable and a __get function ?
*/

class A {
	public $foo='A value for foo';
	function __get($name) {
		echo "<br/>inside __get<br/>";
		if ($name == 'foo') {
			echo "<br/>inside attribute getting for foo<br/>";
			return 'value_from___get';
		}
		return 'default_from___get';
	}
}
$a=new A();
echo "<br/>this is the value ".$a->foo."<br/>";
echo "<br/>this is the value ".$a->someotherproperty."<br/>";
class B {
	function __get($name) {
		echo "<br/>inside __get<br/>";
		if ($name == 'foo') {
			echo "<br/>inside attribute getting for foo<br/>";
			return 'value_from___get';
		}
		return 'default_from___get';
	}
}
$b=new B();
echo "<br/>this is the value ".$b->foo."<br/>";
echo "<br/>this is the value ".$b->someotherproperty."<br/>";
$b->foo="some_new_value_for_foo";
echo "<br/>this is the value ".$b->foo."<br/>";
echo "<br/>this is the value ".$b->someotherproperty."<br/>";
?>
