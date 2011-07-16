<html>
<!--
	This is a solution to Oren's 8'th exercise
-->
<body>
	<form action="sol.php" method="get">
		<h1>Please enter a password to consider</h1>
		<input type="text" name="password"/>
		<input type="submit"/>
	</form>
	<?php
		$password=$_GET['password'];
		$saw_lowercase=false;
		$saw_uppercase=false;
		$two_consecutive=false;
		for($i=0;$i<strlen($password);$i++) {
			$curr=$password{$i};
			if(($curr>='a') && ($curr<='z')) {
				$saw_lowercase=true;
			}
			if(($curr>='A') && ($curr<='Z')) {
				$saw_uppercase=true;
			}
			/*
			if($i>0) {
				if($curr==$previous) {
					$two_consecutive=true;
				}
			}
			$previous=$curr;
			*/
			if($i<strlen($password)-1) {
				if($curr==$password{$i+1}) {
					$two_consecutive=true;
				}
			}
		}
		// lets check if we saw both lower case and upper
		// case letters
		if($saw_uppercase) {
			echo "saw uppercase, that's cool<br/>";
		} else {
			echo "didn't see uppercase<br/>";
		}
		if($saw_lowercase) {
			echo "saw lowercase, that's cool<br/>";
		} else {
			echo "didn't see lowercase<br/>";
		}
		if($two_consecutive) {
			echo "saw two exact same characters in a row<br/>";
		} else {
			echo "no two consecuive characters. That's fine<br/>";
		}
	?>
</body>
</html>
