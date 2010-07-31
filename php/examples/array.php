<?php

/*
	This example shows the case sensitivity of arrays
	and objects.
*/

$array=array();
$array["mark"]="veltzer";
$array["hi"]="ho";
var_dump($array);
echo "<br>this is the attempt to access via case insensitive [".$array["MARK"]."]</br>";
echo "<br>this is the attempt to access via case insensitive [".$array["Mark"]."]</br>";
echo "<br>this is right one [".$array["mark"]."]</br>";

class A {
	public $var="value";
};

$object=new A();
$object->mark="veltzer";
$object->hi="ho";
$object->Var="what?";
var_dump($object);

echo "<br>this is the attempt to access via case insensitive [".$object->Mark."]</br>";
echo "<br>this is the attempt to access via case insensitive [".$object->VAR."]</br>";
echo "<br>this is right one [".$object->var."]</br>";

?>
