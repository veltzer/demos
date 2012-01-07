<!--
This is an example that uses the 'var_dump' variable dumping function to dump
the contents of the build-in _GET and _POST php arrays.
Note that if you want to see this in action you have to use a string of the following
form: http://localhost/~mark/demos/php/examples/showpost_dump.php?a=1&b=2&c=3

	Mark Veltzer <mark@veltzer.net>
-->
<html>
	<body>
		<?php
		echo "</br>printing _GET</br>";
		var_dump($_GET);
		echo "</br>printing _POST</br>";
		var_dump($_POST);
		?>
	</body>
</html>
