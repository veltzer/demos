<?php

class Person {
	// declaring a regular member
	private $name;
	// declaring and initializing a member
	private $age;
	// here is a constructor...
	function __construct($name,$age) {
		$this->name=$name;
		$this->age=$age;
	}
	// here is a __toString method...
	function __toString() {
		return "name is {$this->name}, age is {$this->age}";
	}
}
$my_person=new Person("mark",38);
echo $my_person;
?>
