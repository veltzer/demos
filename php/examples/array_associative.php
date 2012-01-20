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
			echo 'array_key_exists([\'location\'] is'.array_key_exists('location',$me).'</br>';
			echo 'isset($me[\'location\']) is'.isset($me['location']).'</br>';
			echo 'isset($me[\'dontexist\']) is'.isset($me['dontexist']).'</br>';
		?>
	</body>
</html>
