<?php
	session_start();
	$_SESSION['location']=$_POST['location'];
?>
<html>
<body>
	<a href="showsession.php">show my data</a>
</body>
</html>
