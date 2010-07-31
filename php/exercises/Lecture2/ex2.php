<?php
$string = "The quick brown fox"; //this is the original string

//first find 'brown' position and than extract sub string
$sub_string = substr($string,strpos($string,'brown')); 
echo $sub_string;
?>