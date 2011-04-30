<html>
<!--
	This is an example of how to call a function by name
-->
<body>
	<?php
	function add($a,$b) {
		return $a+$b;
	}
	function printit($c) {
		echo $c."\n";
	}
	call_user_func('printit',call_user_func('add',2,2));
	?>
</body>
</html>
