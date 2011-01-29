#!/usr/bin/awk -f
BEGIN {
	FS=":"
	counter=0
}
$3 >= 1000 && $1!="nobody" {
	exit
}
{
	counter++
}
END {
	print "number of system users on your UNIX system is",counter
}
