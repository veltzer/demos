<?php
mysql_connect('localhost', 'root', '');//change username and password to yours

//*****************************
//this block create the table. 
//Execute this only once.

$sql = 'CREATE DATABASE mydatabase';
$result = mysql_query($sql);

mysql_select_db('mydatabase');

$sql = 'CREATE TABLE users(
	username varchar(32),
	password varchar(32),
	first_name varchar(50),
	last_name varchar(50),
	PRIMARY KEY(username))';
$result = mysql_query($sql);
//******************************
mysql_close();
?>