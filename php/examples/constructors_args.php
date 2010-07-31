<?php

/*
This goes to show how to use constructor arguments
*/

class Person {
	private $name;
	private $age;
	// this is an argument based constructor
	// notice that default values are allowed (just like in functions)
	public function __construct($name,$age=18) {
	}
	// this next constructor is an error because we already
	// have a constructor (no method overloading in php)
	//public function __construct($name) {
	//}
}
// this next line is an error (constructor has two mandatory arguments)
//$p=new Person();
echo "<br/>creating a person (both arguments)<br/>";
$p1=new Person("mark",31);
echo "<br/>creating a person (one argument)<br/>";
$p2=new Person("mark");
// notice now compile or runtime error in any of the following
echo "<br/>creating a person (mucho redundant arguments)<br/>";
$p3=new Person("mark",31,"software developer","jazz,philosophy");
?>
