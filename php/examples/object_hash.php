<?php

class hash {
	// the original
	private $original;
	// the setit method 
	public function setit($new_value) {
		$this->original=$new_value;
		#$original=$new_value;
	}
	// the doit method
	public function doit() {
		return md5($this->original);
	}
}
// here comes the user code...
// instantiate/construct one hash object...
$myhash=new hash;
// set the orignal attribute of $myhash. Since the orignal attribute
// of the hash class is private you cannot do something like
// $myhash->original='foobar';
$myhash->setit('foobar');
echo $myhash->doit();
