<?php
$me = array('name'=>'shiflett chirs',
			'location'=>'London, UK');

foreach ($me as $key=>$value)
{
	switch ($key){
		case 'name':
			echo "My name is: $value <br/>";
			break;
		case 'location':
			echo "I live in: $value <br/>";
			break;
	}
}
?>