<?php
$html = file_get_contents('http://www.google.com/'); //get google as a string
$html = str_replace('/search','ex4_search.php',$html);
echo $html;

?>