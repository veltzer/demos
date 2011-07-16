<html>
<!--
	This is a solution to orens 3'rd exercise regarding arrays
-->
<body>
	<form action="sol.php" method="get">
		<h2>Please enter some text</h2>
		<input type="text" name="text" style="width: 100%"/>
		<h2>Please enter words to replace: w1: r1, w2: r2,...</h2>
		<input type="text" name="rep" style="width: 100%"/>
		<input type="submit"/>
	</form>
	<?php
		function string_to_aso_arr($rep) {
			$arr=split(',',$rep);
			$ret=Array();
			foreach($arr as $word) {
				$couple=split(':',$word);
				$s_w=trim($couple[0]);
				$s_r=trim($couple[1]);
				$ret[$s_w]=$s_r;
			}
			return $ret;
		}
		function replace($text,$aso_arr) {
			$arr=split(' ',$text);
			$list=Array(); # used as an enumerated array
			foreach($arr as $word) {
				if(array_key_exists($word,$aso_arr)) {
					$list[]=$aso_arr[$word];
				} else {
					$list[]=$word;
				}
			}
			return join(' ',$list);
		}
		$text=$_GET['text'];
		$rep=$_GET['rep'];
		$aso_arr=string_to_aso_arr($rep);
		$newtext=replace($text,$aso_arr);
		echo "the newtext is:<br/>$newtext";
	?>
</body>
</html>
