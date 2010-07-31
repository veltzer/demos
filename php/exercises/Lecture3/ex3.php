<?php
$string = 'crylove=johnhiatt&newslang=shins'; //original string
$split1 = explode('&',$string); //split by '&'
foreach ($split1 as $substring) //go over splitted array
{
	list($name,$value) = explode('=',$substring); //split sub-string by '='
	$array[$name] = $value; //assing to new array
}
?>
<pre>
<?php print_r($array); ?>
</pre>