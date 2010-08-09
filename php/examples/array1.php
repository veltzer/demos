e<html>
<!--
	This is a solution to the first exercise regarding
	arrays: enumerated array
-->
<body>
	<?php
		$favs=array();
		$favs[]='this is the end';
		$favs[]='my funny valentine';
		$favs[]='all the things you are';
		// now lets add ' rocks!' to all the elements
		// of the array...
		for($i=0;$i<count($favs);$i++) {
			$favs[$i].=' rocks!';
		}
		// now lets print the array
		foreach($favs as $fav) {
			echo $fav.'<br/>';
		}
		// or another option
		print_r($favs);
	?>
</body>
</html>
