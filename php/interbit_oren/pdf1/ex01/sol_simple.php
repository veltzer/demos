<html>
<!--
	This is a solution to orens 1'st exercise
-->
<body>
	<?php
		// TODO: remove the hardcoded value...
		$number=1973;
		// lets check if the number is positive
		if($number>0) {
			echo "the number is positive";
		} else {
			// we are here only if the number is <=0
			if($number==0) {
				echo "the number is 0";
			} else {
				// now were sure that the number is
				// negative
				echo "the number is negative";
			}
		}
	?>
</body>
</html>
