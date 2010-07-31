<?php

class Person {
	// declaring a regular member
	private $name;
	// declaring and initializing a member
	private $age=18;
	// declaring and initializing a static member
	static private $num_persons=0;
	// defining a constructor (notice not naming the class, which is good)
	// notice, no accessor
	public function __construct() {
		// accessing the static member
		self::$num_persons++;
	}
	// defining a destructor (again, notice not naming the class, which is good)
	function __destruct() {
		echo "<br/>";
		echo "help, ".$this->name." is dying";
		echo "<br/>";
	}
	public static function get_num_persons() {
		// accessing the static member
		return self::$num_persons;
	}
	public function getName() {
		return $this->name;
	}
	public function getAge() {
		return $this->age;
	}
	public function setName($iname) {
		// the next line is an error which, unfortunately, will get silently
		// ignored
		// $name=$iname;
		// this is the right one:
		$this->name=$iname;
	}
}
$person=new Person;
$person->setName("mark");
echo "name is ".$person->getName();
echo "<br/>";
echo "age is ".$person->getAge();
echo "<br/>";
echo "num persons is ".Person::get_num_persons();
echo "<br/>";
// look at how we can install new attributes at runtime
$person->foo="what?!?";
echo "foo is ".$person->foo;
echo "<br/>";
$person=null;
$newperson=new Person;
$newperson->setName("shelly");
// notice that we do not destroy the object but it will still show the destructor
//$newperson=null;
?>
