<?php
//this script create the table, therefore should be executed only once.

$sql = "CREATE TABLE users (
		username VARCHAR (32) , 
		password VARCHAR (32) , 
		first_name VARCHAR (50) , 
		last_name VARCHAR (50) , 
		PRIMARY KEY(username) )";

$db_handle = sqlite_open('lecture6.sqlite') or exit("Database could not be open");
sqlite_query($db_handle,$sql);


?>