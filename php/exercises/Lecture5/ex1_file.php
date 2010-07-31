<?php
//**********************************
//Read from file to string
$filename = 'ex1_students.txt';
$file_handle = fopen($filename,'r'); //open file for reading
$string = fread($file_handle,filesize($filename)); 
fclose($file_handle);
//**********************************
$file_arr = explode("\n",$string); //on windows, delimiter should be "\r\n"
foreach ($file_arr as $line)
{
	$line_arr = explode(',',$line);
	echo "\"{$line_arr[0]} {$line_arr[1]}\" &lt;{$line_arr[2]}&gt; <br/>";
}

?>