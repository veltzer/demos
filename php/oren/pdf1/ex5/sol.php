<html>
<!--
	This is a solution to orens 3'rd exercise
-->
<body>
	<h1>Please enter a number</h1>
	<form action="sol.php" method="get">
		<h1>Please enter the base value</h1>
		<input type="text" name="base" />
		<h1>Please enter the number of lines</h1>
		<input type="text" name="lines" />
		<input type="submit"/>
	</form>
	<?php
		$base=$_GET['base'];
		$lines=$_GET['lines'];
		for($i=0;$i<$lines;$i++) {
			for($j=0;$j<$base+$i;$j++) {
				echo '*';
			}
			echo '<br/>';
		}
	?>
</body>
</html>
