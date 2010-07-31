<?php
$foo = 3;
$bar = 5;
swap($foo,$bar);
echo "\$foo = $foo <br/>";
echo "\$bar = $bar <br/>";

/*
 * prints:
	$foo = 5
	$bar = 3 
 * 
 */

function swap(&$var1, &$var2)
{
	$temp_var = $var1;
	$var1 = $var2;
	$var2 = $temp_var;
}
?>