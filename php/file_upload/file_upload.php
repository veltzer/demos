<?php

// "upload" is the name assigned to the input element, as in
// // <input type="file" name="upload" />
if (isset($_FILES['upload'])) {
	if ($_FILES['upload']['error'] > 0) {
		echo "error occurred<br/>";
		echo $_FILES['upload']['error'];
	} else {
		$tmp=$_FILES['upload']['tmp_name'];
		$dst="/tmp/{$_FILES['upload']['name']}";
		if(!move_uploaded_file($tmp,$dst)) {
			echo "error occured during moving of file";
		} else {
			echo "upload was ok and file is on server at $dst";
		}
	}
}
?>
