<?php
$handle=fopen('/tmp/syslog','r');
if($handle) {
	while(($buffer=fgets($handle, 4096))!==false) {
		echo $buffer;
	}
	if(!feof($handle)) {
		echo "Error: unexpected fgets() fail\n";
	}
	fclose($handle);
} else {
	echo "error! could not open file";
}
?>
