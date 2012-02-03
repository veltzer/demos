#!/usr/bin/awk -f

# this program calculates the average UID on a UNIX system

BEGIN {
	FS=":"
	sum=0
}
{
	sum+=$3
}
END {
	print "average UID is",sum/NR
}
