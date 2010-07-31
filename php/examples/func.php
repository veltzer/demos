<?php
	function myloop($string,$count=1) {
		for($i=0;$i<$count;$i++) {
			echo $string.' i is '.$i.'<br/>';
		}
	}
	myloop('Hello, World!',5);
	echo '=====================<br/>';
	myloop('Hello, World!');
?>
