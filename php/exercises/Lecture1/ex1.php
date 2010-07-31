<?php
/*
 * This handles the POST request:
 */
if (isset($_POST['username']))
{
	echo "Recieved by POST:<br/>";
	if ($_POST['username']==='chris')
	{
		echo "Welcome back, {$_POST['username']} !!!";
	}
	else
	{
		echo "Welcome {$_POST['username']}";
	}
	
}
/*
 * This handles the GET request:
 */
if (isset($_GET['username']))
{
	echo "Recieved by GET:<br/>";
	if ($_GET['username']==='chris')
	{
		echo "Welcome back, {$_GET['username']} !!!";
	}
	else
	{
		echo "Welcome {$_GET['username']}";
	}
	
}
?>