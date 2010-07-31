<html>
<!--
	This is a solution to Oren's 10'th exercise
-->
<body>
	<form action="sol.php" method="get">
		<h1>Please enter text as k1:v1; k2:v2,...</h1>
		<input type="text" name="text"/>
		<input type="submit"/>
	</form>
	<?php
		$text=$_GET['text'];
		$arr=split(';',$text);
		$result=Array();
		foreach($arr as $couple) {
			$zug=split(':',$couple);
			$key=trim($zug[0]);
			$value=trim($zug[1]);
			$result[$key]=$value;
		}
		var_dump($result);
	?>
</body>
</html>
