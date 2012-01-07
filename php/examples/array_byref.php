<!--
This example explores what happens when you pass an array by reference.
This is a very controvertial feature of php whereby arrays passed by reference
are passed by reference but get copied with the first modifying operation.
Very tricky. Take heed.

	Mark Veltzer <mark@veltzer.net>
-->
<html>
	<body>
		<?php
		function addElements($arr) {
			$arr[]="three";
			$arr[]="four";
		}
		$arr=array();
		$arr[]="one";
		$arr[]="two";
		addElements();
		var_dump($arr);
		?>
	</body>
</html>
