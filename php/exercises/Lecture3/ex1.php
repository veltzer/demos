<?php
//initiate the songs array
$favorites = array('All My Loving',
					'Michelle',
					'Lucy in the Sky With Diamonds',
					'Yellow Submarine');

//change the array values
for($i=0;$i<count($favorites);$i++)
{
	$favorites[$i].=' rocks!';
}
//print array values
foreach($favorites as $song)
{
	echo "$song <br/>";	
}

?>