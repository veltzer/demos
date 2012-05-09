<?php
/*
	Mark Veltzer <mark.veltzer@gmail.com>

Important:
	This comment itself must remain in the PHP part of the text
	because otherwise it will go to the client as part of the json
	response and, because json does not support comments, will cause
	an error at the client side.
*/
$directory=$_GET['path'];
echo "{ label: 'name', identifier: 'path', items: [ ";
if(is_dir($directory)) {
$handler = opendir($directory);
while ($file = readdir($handler)) {
	if ($file != '.' && $file != '..') {
		$path=$directory.$file;
		if(is_dir($path)) {
			echo "{ path: '",$path,"', name: '",$file,"', type:'item' ,children: [ { path: '", $path,"', type:'sub'} ]},";
		} else {
			echo "{ path: '",$path,"', name: '",$file,"', type:'item' },";
		}
	}
}
closedir($handler);
} else {
	echo "{ label: 'name', identifier: 'path' }";
}

echo "] }";
?>
