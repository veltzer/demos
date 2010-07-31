<html>
<!--
	This is a solution to orens 3'rd exercise
-->
<body>
	<h1>Please enter a number</h1>
	<form action="sol_complex.php" method="get">
		<input type="text" name="base" />
		<input type="text" name="lines" />
		<input type="submit"/>
	</form>
	<?php
		$base=$_GET['base'];
		$lines=$_GET['lines'];
		$rowstr='';
		for($j=0;$j<$base;$j++) {
			$rowstr.='*';
		}
		for($i=0;$i<$lines;$i++) {
			echo $rowstr.'<br/>';
			$rowstr.='*';
		}
	?>
</body>
</html>
