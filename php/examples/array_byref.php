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
		function addElementsWrong($arr) {
			$arr[]="three";
			$arr[]="four";
		}
		function addElementsRight(&$arr) {
			$arr[]="five";
			$arr[]="six";
		}
		$arr=array();
		$arr[]="one";
		$arr[]="two";
		addElementsWrong($arr);
		addElementsRight($arr);
		var_dump($arr);
		?>
	</body>
</html>
