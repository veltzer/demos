<html>
<!--
	This is a solution to orens 2'nd exercise regarding arrays
-->
<body>
	<form action="sol.php" method="get">
		<h2>Please enter some text</h2>
		<input type="text" name="text" />
		<input type="submit"/>
	</form>
	<?php
		function count_words($text) {
			$arr=split(' ',$text);
			$res=Array();
			foreach($arr as $word) {
				if(array_key_exists($word,$res)) {
					$res[$word]+=$res[$word]+1;
				} else {
					$res[$word]=1;
				}
			}
			return $res;
		}
		$text=$_GET['text'];
		$result_array=count_words($text);
		var_dump($result_array);
	?>
</body>
</html>
