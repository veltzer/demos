<?php

/*
The purpose of this example is to explain that adding attributes is possible
Will this work with strict ? Don't know
*/

class A {
	public $name="default";
}
$a=new A();
echo "<br/>Before the big change<br/>";
var_dump($a);

$a->newattr="new value";

echo "<br/>After the big change<br/>";
var_dump($a);
?>
