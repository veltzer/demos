<?php
$cols=$_GET['cols'];
$rows=$_GET['rows'];
$position=$_GET['position'];
echo '{"data":[';
for($r=0;$r<$rows;$r++) {
	echo '[';
	for($c=0;$c<$cols;$c++) {
		echo ($position+$r).'.'.$c; 
		if($c<$cols-1) {
			echo ',';
		}
	}
	echo ']';
	if($r<$rows-1) {
		echo ',';
	}
}
echo ']}';
?>
