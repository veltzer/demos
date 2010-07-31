<?php
class Person {
	var $name;
	function printName() {
		echo 'my name is '.$this->name;
	}
}
$person=new Person();
$person->name='mark';
$person->printName();
?>
