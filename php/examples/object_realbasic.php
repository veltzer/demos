<?php

class Person {
	// declaring a regular member
	var $name;
	// declaring and initializing a member
	var $age;
	// method to print this object
	function printMe() {
		echo "<br/>".$this->name."<br/>";
		echo "<br/>".$this->age."<br/>";
	}
	// here is a constructor...
	function Person() {
		$this->name="mark";
		$this->age=38;
	}
}
$arr=array();
for($i=0;$i<100;$i++) {
	$p=new Person;
	//$p->name="foo".$i;
	//$p->age=$i;
	$arr[]=$p;
}
for($i=0;$i<100;$i++) {
	$arr[$i]->printMe();
}
/*
$my_person=new Person;
$my_person->name="mark";
$my_person->age=38;
$my_person->printMe();

$my_person2=new Person;
$my_person2->name="yossi";
$my_person2->age=13;
$my_person2->printMe();
 */
?>
