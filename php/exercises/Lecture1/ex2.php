<?php
/*
 * This handles the POST request:
 */

//validate that submitted data is actually numbers
if ( isset($_POST['age']) && ctype_digit($_POST['age']) && $_POST['age']>0)
{
	$i=0;
	while ($i<$_POST['age']){
		echo "$i <br/>";
		$i+=2;	
	}
}
else
{
	echo 'Invalide Data!';
}
?>