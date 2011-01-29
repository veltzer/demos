#!/usr/bin/awk -f
BEGIN {
	FS=":"
	counter=0
}
$1 ~ /^user[0-9][0-9]$/ {
	counter++
}
END {
	print "counter is",counter
}
