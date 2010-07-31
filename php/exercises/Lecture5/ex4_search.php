<?php
echo "You are actually on a customized local page";
readfile("http://www.google.com/search?q={$_GET['q']}");
?>