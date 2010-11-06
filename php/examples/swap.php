<?php
	function swap(&$a,&$b) {
		$tmp=$a;
		$a=$b;
		$b=$tmp;
	}
	function swap2(&$a,&$b) {
		$a=$a^$b;
		$b=$a^$b;
		$a=$b^$a;
	}
	$foo=5;
	$bar=7;
	swap2($foo,$bar);
	echo "foo is $foo<br/>";
	echo "bar is $bar<br/>";
	echo "===========<br/>";
	$t=5;
	$y=7;
	swap2($t,$y);
	echo "t is $t<br/>";
	echo "y is $y<br/>";
?>
