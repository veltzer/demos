<html>
<!--
	This is a piece of code which prints all the prime numbers
	from 2 to a 100... Very trivial algorith.
	One way to improve on it would be to scan from 2 to sqrt(100)
	instead of to 100-1. Another way would be some variation
	on the Eucledean algorithm...
-->
<body>
	<?php
		for($i=2;$i<100;$i++) {
			$prime = true;
			for($x=2;$x<$i-1;$x++) {
				if($i%$x==0) {
					$prime=false;
				}
			}
			if($prime==true) {
				echo $i." ";
			}
		}
	?>
</body>
</html>
