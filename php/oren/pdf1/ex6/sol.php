<html>
<!--
	This is a solution to orens 6'th exercise
-->
<body>
	<form action="sol.php" method="get">
		<h1>Please enter a number</h1>
		<input type="text" name="number" />
		<h1>Please enter a divider</h1>
		<input type="text" name="div" />
		<input type="submit"/>
	</form>
	<?php
		$number=$_GET['number'];
		$div=$_GET['div'];
		for($i=0;$i<$number;$i++) {
			if($i%$div==0) {
				echo $i.'<br/>';
			}
		}
	?>
</body>
</html>
