<?php
//**********************************
//Read from file to array of lines
$filename = 'ex1_students.txt';
$file_arr = file($filename); 
//**********************************
foreach ($file_arr as $line)
{
	$line_arr = explode(',',$line);
	echo "\"{$line_arr[0]} {$line_arr[1]}\" &lt;{$line_arr[2]}&gt; <br/>";
}

?>