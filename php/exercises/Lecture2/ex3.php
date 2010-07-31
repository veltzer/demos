<?php
$string = "The quick brown fox"; //this is the original string


$one = substr($string,0,strpos($string,'brown')-1);
$two = substr($string,strpos($string,'brown')+strlen('brown')+1);
 
echo "$one red $two" ;
?>