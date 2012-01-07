<?php
function getDirectoryRec($path,$level,&$arr) {
	$ignore=array('.','..');
	if(!is_dir($path)) {
		return;
	}
	$dh=opendir($path);
	while(false!==($file=readdir($dh))) {
		if(in_array($file,$ignore)) {
			break;
		}
		$current=$path.'/'.$file;
		if(is_dir($current)) {
			getDirectoryRec($current,$level+1,$arr);
		}
		if(is_file($current)) {
			$arr[]=$file;
		}
	}
	closedir($dh);
}
function getDirectory($path='.',$level=0) {
	$arr=array();
	getDirectoryRec($path,$level,$arr);
	return $arr;
}
//var_dump(getDirectory($path='/var/www/apt'));
?>
