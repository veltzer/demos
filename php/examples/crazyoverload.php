<?php

/*
 * This is a solution to the crazy overload exercise
*/

class CrazyOverload {
	private $valname='default name';
	private $valage=15;
	function __get($name) {
		if($name=='name') {
			return $this->valage;
		}
		if($name=='age') {
			return $this->valname;
		}
		// error?!?
	}
	function __set($name,$val) {
		if($name=='name') {
			$this->valname=$val;
		}
		if($name=='age') {
			$this->valage=$val;
		}
		// error?!?
	}
}

$a=new CrazyOverload;
$a->name='first name';
$a->age=4;
echo $a->name.'<br/>'.$a->age.'<br/>';
?>
