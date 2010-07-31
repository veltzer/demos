<?php
if (isset($_POST['string'])) //form was submitted
{
	$word_count = str_word_count($_POST['string']);
	$the_count = substr_count($_POST['string'],'the');
	echo "Entered string: {$_POST['string']} <br/>";
	echo "Total number of words: $word_count <br/>";
	echo "'the' occures $the_count times, which are ".(int)($the_count/$word_count*100) .'%'; 
}
?>


<form action="process.php" method="POST">
<p><input type="text" name="string" /></p>
<p><input type="submit" /></p>
</form>