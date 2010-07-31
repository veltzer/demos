<?php
echo "First Test: <br/>";
myloop('Hello, World!', 3);
echo "Second Test: <br/>";
myloop('Hello, World!');

function myloop($str, $num=1)
{
	for($i=1;$i<=$num;$i++)
	{
		echo "$str <br/>";
	}
}
?>