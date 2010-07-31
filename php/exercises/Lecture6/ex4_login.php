<?php
if ( strlen($_POST['username'])>0 && strlen($_POST['password'])>0 )
{
	if (verify_login($_POST['username'],$_POST['password'])) //see function definition belows
	{
		echo "Login Success!";
	}
	else
	{
		echo "Login Failed!";
	}
}
else
{
	echo "Please enter username and password.";
}

function verify_login($username,$password)
{
	//change DB username and password to yours
	mysql_connect('localhost', 'root', '') or exit ("Cannot connect to Database");
	mysql_select_db('mydatabase');
	$sql = "SELECT username FROM users 
			WHERE username='$username' AND 
					password=md5('$password') ;";
	
	$result = mysql_query($sql);
	mysql_close(); //Important!! Close DB before 'return' statement
	
	$user = mysql_fetch_assoc($result);
	//if no line return from DB, then $user is FALSE
	return ($user !== false); 
	
	
	
}
?>