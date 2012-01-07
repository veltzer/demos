<!--
A piece of code that dumps the _POST and _GET arrays via iteration.
When you iterate them in a dictionary fashion (the standard way), you
get them as key,value pairs. When you iterate them via values only
you only get the values (this is standard php array behaviour).

	Mark Veltzer <mark@veltzer.net>
-->
<html>
	<body>
		<?php
		echo "<h1>printing get</h1>";
		foreach($_GET as $value) {
			echo "<p>get value is [$value]</p>";
		}
		foreach($_GET as $key=>$value) {
			echo "<p>get key is [$key] and value is [$value]</p>";
		}
		echo "<h1>printing post</h1>";
		foreach($_POST as $value) {
			echo "<p>post value is [$value]</p>";
		}
		foreach($_POST as $key=>$value) {
			echo "<p>post key is [$key] and value is [$value]</p>";
		}
		?>
	</body>
</html>
