<?php
	// this is an example of a simple php string templating solution using the 'str_replace' function...
	function format($str,$vars) {
		foreach($vars as $k => $v) {
			$str=str_replace("\$$k",$v,$str);
		}
		return $str;
	}
	// and here is how we use it...
	$vars=array();
	$vars['foo']='bar';
	$vars['zoo']='boo';
	echo format('this string is $foo and $zoo',$vars).'<br/>';
?>
