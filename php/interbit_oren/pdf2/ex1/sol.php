<html>
<!--
	This is a solution to orens 1'st exercise regarding arrays
-->
<body>
	<form action="sol.php" method="get">
		<h2>Please enter some text</h2>
		<input type="text" name="text" />
		<input type="submit"/>
	</form>
	<?php
		function make_unique($text) {
			$arr=split(' ',$text);
			$res=Array(); # use associatevily (hash)
			$list=Array(); # used an enumerated array
			foreach($arr as $word) {
				# seeing the word for the
				# first time
				if(!array_key_exists($word,$res)) {
					$list[]=$word;
					$res[$word]=true;
				}
			}
			return join(' ',$list);
		}
		$text=$_GET['text'];
		$unique_text=make_unique($text);
		echo "the result is $unique_text";
	?>
</body>
</html>
