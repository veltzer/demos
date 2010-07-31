<?php
	$string='the quick brown fox';
	for($i=0;$i<strlen($string);$i++) {
		$current=$string{$i};
		if($current==' ' || $i==0) {
			$string{$i+1}=strtoupper($string{$i+1});
		}
	}
	echo $string;
?>
