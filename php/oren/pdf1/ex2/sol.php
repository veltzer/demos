<html>
<!--
	This is a solution to orens 3'rd exercise
-->
<body>
	<h1>Please enter a number</h1>
	<form action="sol.php" method="get">
		<h1>Please enter the number of kids in the class</h1>
		<input type="text" name="kidnum" />
		<h1>Please enter the fee to enter</h1>
		<input type="text" name="entrancefee" />
		<h1>Please enter the bus fee</h1>
		<input type="text" name="busfee" />
		<input type="submit"/>
	</form>
	<?php
		$kidnum=$_GET['kidnum'];
		$entrancefee=$_GET['entrancefee'];
		$busfee=$_GET['busfee'];
		$fee_per_kid=($entrancefee+$busfee)/$kidnum;
		echo "Each kid will have to pay ".$fee_per_kid;
	?>
</body>
</html>
