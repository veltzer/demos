<html>
<!--
	This is an example of two thing:
	1. How to write a form.
	2. How to process the incoming GET or POST paramters from within PHP.
-->
<body>
	<?php
	echo "<h1>printing _GET</h1>";
	var_dump($_GET);
	echo "<h1>printing _POST</h1>";
	var_dump($_POST);
	?>
	<h1>This is a form based on GET</h1>
	<form action="form.php" method="get">
		<input type="text" name="input1" />
		<input type="text" name="input2" />
		<input type="submit"/>
	</form>
	<h1>This is a form based on POST</h1>
	<form action="form.php" method="post">
		<input type="text" name="input1" />
		<input type="text" name="input2" />
		<input type="submit"/>
	</form>
</body>
</html>
