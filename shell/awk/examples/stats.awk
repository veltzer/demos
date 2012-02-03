#!/usr/bin/awk -f
BEGIN {
	counter=0
	print $0
}
/ENVIRON["VAL"]/ {
	counter++
}
END {
	print ENVIRON["VAL"]
	print "saw it " counter " times"
}
