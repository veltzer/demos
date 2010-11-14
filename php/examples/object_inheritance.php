<?php

class Person {
	// declaring the name field
	protected $name;
	// declaring the age field 
	protected $age;
	// constructor for this class, receives name and age
	public function __construct($name,$age) {
		echo "Hey!, im in the Person constructor...<br/>";
		$this->name=$name;
		$this->age=$age;
	}
	public function getName() {
		return $this->name;
	}
	public function setName($name) {
		$this->name=$name;
	}
	public function getAge() {
		return $this->age;
	}
	public function setAge($age) {
		$this->age=$age;
	}
	public function printYourself() {
		echo "<br/>my name is ".$this->name;
		echo "<br/>my age is ".$this->age;
	}
}
/*
$my_person=new Person("mark",38);
$my_person->printYourself();
$my_person->setName("foobar");
$my_person->printYourself();
 */

class Employee extends Person {
	// employee must have a salary...
	private $salary;
	// getter and setter for salary...
	public function getSalary() {
		return $this->salary;
	}
	public function setSalary($salary) {
		$this->salary=$salary;
	}
	// constructor. Remmember to call the PARENT!!!
	public function __construct($name,$age,$salary) {
		echo "Hey, I'm in the constructor of the child";
		parent::__construct($name,$age);
		$this->salary=$salary;
	}
	public function printYourself() {
		//parent::printYourself();
		echo "<br/>my name is ".$this->name;
		echo "<br/>my age is ".$this->age;
		echo "<br/>my salary is ".$this->salary;
	}
}
function do_something_with_object($obj) {
	$obj->setName("foobar");
}

$my_child=new Employee("mark",38,13);
$my_child->printYourself();
do_something_with_object($my_child);
$my_child->printYourself();

?>
