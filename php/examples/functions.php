<?php
/*
	This is a demo of php functions, from simple, to mandatory
	arguments, to vararg functions
*/

// A simple no arguments function, can be called with no or many arguments
// and disregards these arguments even if they are passed
function hello() {
	echo "<br/>Hello<br/>";
}
// A two argument function
// both of the following two arguments are mandatory
// calling with less than 2 arguments is a compile time error!
// However, the method can be called with more than 2 arguments
// which it will disregard. Calling with more than the 2 mandtory
// arguments is not a compile or runtime error!
function add($a,$b) {
	return $a+$b;
}
// this makes sense, now we can call with a single argument
// calling with less than 1 argument is a compile time error
// calling with 1 or more is not any type of error
function defaultvalueadd($a,$b=1) {
	return $a+$b;
}
// the next one is stupid - we need to call with two args and cannot
// use the default value for the first argument
function thisisstupid($a=1,$b) {
	return $a+$b;
}
// this function looks like it doesn't get any arguments but in fact
// it gets as many arguments as you'll send. You can see that from
// the method signature, however.
function multiadd() {
	// this will add all arguments to it
	$args=func_get_args();
	$sum=0;
	for($i=0;$i<count($args);$i++) {
		$sum+=$args[$i];
	}
	return $sum;
}
// same as before, but calling another multi argument function with as many
// arguments as you'd like
function submultiadd() {
	$args=func_get_args();
	$res=call_user_func_array('multiadd',$args);
	return -$res;
}
// here is another example, calling a multi-argument function from a 2 argument
// function
function add2($a,$b) {
	$ar=array();
	$ar[]=$a;
	$ar[]=$b;
	$res=call_user_func_array('multiadd',$ar);
	return $res;
}
// next line will cause an error (function needs 2 mandatory arguments)
// add(5)
// this line is OK
$res=add(5,6); // result=11 
echo "<br/>add(5,6) is $res</br>";
// this is also OK ?!? Will it work with strict ?
$res=add(5,6,7,8,9,10); // result=11
echo "<br/>add(5,6,7,8,9,10) is $res</br>";
// the next line is illegal in php
// $e=thisisstupid(,6);
// nor is the next line:
// $e=thisisstupid(6);
// and that's why thisisstupid is stupid
// now we will call it properly and pass 2 args (and
// waiver the right to a default argument)
$res=thisisstupid(5,6);
echo "<br/>thisisstupid(5,6) is $res<br/>";
// this is still legal, though
$res=thisisstupid(2,3,4,5,6,7,8,9);
echo "<br/>thisisstupid(2,3,4,5,6,7,8,9) is $res<br/>";
// this is much better (passing any number of arguments)
$res=multiadd(5,6,7,8,9,10);
echo "<br/>multiadd(5,6,7,8,9,10) is $res<br/>";
// calling a multi argument function that calls another multi argument function 
$res=submultiadd(5,6,7,8,9,10);
echo "<br/>submultiadd(5,6,7,8,9,10) is $res<br/>";
// calling a fixed number of arguments function which calls a multi argument one
$res=add2(9,10);
echo "<br/>add2(9,10) is $res<br/>";
?>
