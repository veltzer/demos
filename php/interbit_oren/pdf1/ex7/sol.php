<html>
<!--
	This is a solution to Oren's 7'th exercise
-->
<body>
	<form action="sol.php" method="get">
		<h1>Please enter a string</h1>
		<input type="text" name="string"/>
		<input type="submit"/>
	</form>
	<?php
		$string=$_GET['string'];
		$abc="abcdefghijklmnopqrstuvwxyz";
		for($i=0;$i<strlen($abc);$i++) {
			$curr=$abc{$i};
			if(substr_count($string,$curr)==0) {
				echo $curr."<br/>";
			}
		}
	?>
</body>
</html>
