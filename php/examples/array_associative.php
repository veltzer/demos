<!--
This example shows how to use associative arrays in PHP.

	Mark Veltzer <mark@veltzer.net>
-->
<html>
	<body>
		<?php
			// lets create an associative array...
			$me=array();
			// now lets put some data in it...
			$me['location']='ramat-gan';
			$me['name']='mark';
			// now lets print the array
			foreach($me as $k => $v) {
				if($k=='name') {
					echo 'my name is '.$v.'</br>';
				}
				if($k=='location') {
					echo 'my location is '.$v.'</br>';
				}
			}
			$v=isset($me['location']);
			echo 'isset[\'location\'] is'.$v.'</br>';
			$v=isset($me['dontexist']);
			echo 'isset[\'dontexist\'] is'.$v.'</br>';
		?>
	</body>
</html>
