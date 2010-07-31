<?php
$db_handle = sqlite_open('lecture6.sqlite') or exit("Database could not be open");

$sql = "INSERT INTO users VALUES ('chris','".md5('mypass')."','Chris','Shiflett') ;";
sqlite_query($db_handle,$sql);
$sql = "INSERT INTO users VALUES ('rasmus','".md5('mypass')."','Rasmus','Lerdorf') ;";
sqlite_query($db_handle,$sql);
$sql = "INSERT INTO users VALUES ('andi','".md5('mypass')."','Andi','Gutman') ;";
sqlite_query($db_handle,$sql);

?>
