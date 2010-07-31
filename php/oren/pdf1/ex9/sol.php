<html>
<!--
	This is a solution to Oren's 9'th exercise
-->
<body>
	<form action="sol.php" method="get">
		<h1>Please enter numbers separated by commas</h1>
		<input type="text" name="numbers"/>
		<input type="submit"/>
	</form>
	<?php
		$numbers=$_GET['numbers'];
		$arr=split(',',$numbers);
		$sum=0;
		foreach($arr as $num) {
			$sum+=$num;
		}
		echo 'sum is '.$sum;
	?>
</body>
</html>
