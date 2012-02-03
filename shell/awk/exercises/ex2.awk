#!/usr/bin/awk -f

# program to calculate the average size of a group on a UNIX
# system

BEGIN {
	FS=":"
	sum=0
}
{
	sum+=split($4,arr,",")
	delete arr
}
END {
	print "average group size is",sum/NR
}
