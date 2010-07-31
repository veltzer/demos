<html>
<!--
	This is a solution to orens 6'th exercise
-->
<body>
	<form action="perfect.php" method="get">
		<h1>Please enter a number</h1>
		<input type="text" name="number" />
		<input type="submit"/>
	</form>
	<?php
		$number=$_GET['number'];
		for($i=0;$i<$number;$i++) {
			$sum=0;
			for($j=1;$j<$i;$j++) {
				if($i%$j==0) {
					$sum+=$j;
				}
			}
			if($i==$sum) {
				// wow, weve found a perfect number
				echo $i.'<br/>';
			}
		}
	?>
</body>
</html>
