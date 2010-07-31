<?php
class myclass
{
	var $myproperty;
	function mymethod()
	{
		echo "\$myproperty is: {$this->myproperty}";
		/*this will output:
		 * $myproperty is: ...
		 */
	}
}

$myobject = new myclass;
$myobject->myproperty = 'Hello, World!';
$myobject->mymethod();
?>