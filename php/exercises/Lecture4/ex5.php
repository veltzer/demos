<?php
class hash
{
	private $original;
	function setit($string)
	{
		$this->original = $string;
	}
	function doit()
	{
		return md5($this->original);
	}
}

$myhash = new hash;
$myhash->setit('The quick brown fox');
$result = $myhash->doit();
echo 'Original string was: "The quick brown fox"<br/>';
echo "Hashed string is: $result";

?>