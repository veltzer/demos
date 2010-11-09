<?php

/*
	This is a demo of php functions, from simple, to mandatory
	arguments, to vararg functions
*/

function hello() {
	echo "<br/>Hello<br/>";
}
// both of the following two arguments are mandatory
function add($a,$b) {
	return $a+$b;
}
// this make sense, now we can call with a single argument
function defaultvalueadd($a,$b=1) {
	return $a+$b;
}
// the next one is stupid - we need to call with two args
function thisisstupid($a=1,$b) {
	return $a+$b;
}
function multiadd() {
	// this will add all arguments to it
	$args=func_get_args();
	$sum=0;
	for($i=0;$i<count($args);$i++) {
		$sum+=$args[$i];
	}
	return $sum;
}
function submultiadd() {
	$args=func_get_args();
	$res=call_user_func_array('multiadd',$args);
	return -$res;
}
// next line will cause an error (function needs 2 mandatory arguments)
// add(5)
// this line is OK
$c=add(5,6); // $c=11
// this is also OK ?!? Will it work with strict ?
$c=add(5,6,7,8,9,10); // $c=11
// the next line is illegal in php
// $e=thisisstupid(,6);
// and that's why thisisstupid is stupid
// now we will call it properly and pass 2 args (and
// waiver the right to a default argument)
$e=thisisstupid(5,6);
echo "<br/>e is $e<br/>";
// this is still legal, though
$f=thisisstupid(2,3,4,5,6,7,8,9);
// this is much better
$c=multiadd(5,6,7,8,9,10);
echo "<br/>c is $c<br/>";
// how does this work ?
$d=submultiadd(5,6,7,8,9,10);
echo "<br/>d is $d<br/>";
?>
