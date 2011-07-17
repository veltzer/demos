<html>
<!--
	This is a solution to orens 3'rd exercise
-->
<body>
	<h1>Please enter a number</h1>
	<form action="sol.php" method="get">
		<input type="text" name="cols" />
		<input type="text" name="rows" />
		<input type="submit"/>
	</form>
	<?php
		$cols=$_GET['cols'];
		$rows=$_GET['rows'];
		for($i=0;$i<$rows;$i++) {
			for($j=0;$j<$cols;$j++) {
				echo '*';
			}
			echo '<br/>';
		}
	?>
</body>
</html>
