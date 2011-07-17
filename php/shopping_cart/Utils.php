<?php

error_reporting(E_ALL);
ini_set('display_errors',1);

# function that gets an error message and triggers an error for the whole page
function my_error($msg) {
	header('HTTP/1.1 500 Internal Server Error');
	trigger_error($msg,E_USER_ERROR);
}

?>
