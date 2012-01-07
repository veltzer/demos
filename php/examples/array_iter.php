<!--
This piece show that you can iterate an associative array just like a regular
array but you will get only the values of that array.

	Mark Veltzer <mark@veltzer.net>
-->
<html>
	<body>
		<?php
		$arr=array();
		$arr['a']=1;
		$arr['b']=2;
		$arr['c']=3;
		echo "<br/>iterating key,value style...<br/>";
		foreach($arr as $key=>$value) {
			echo "key is [$key], value is [$value]</br>";
		}
		echo "<br/>iterating flat style...<br/>";
		foreach($arr as $value) {
			echo "value is [$value]</br>";
		}
		?>
	</body>
</html>
