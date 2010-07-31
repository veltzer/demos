<html>
<body>
	<?php
		$foo='bar';
		echo "foo is $foo ";
		echo 'foo is $foo ';
		echo 'foo is '.$foo.' ';

		$other_foo="this is some other $foo value";
		echo "other_foo if $other_foo";

		echo 'this is the value '.$_GET['foo'];

		echo 'I don\'t like vanilla';
		echo "I don\"t like vanilla";
	?>
</body>
</html>
