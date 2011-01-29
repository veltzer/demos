#!/usr/bin/awk -f
BEGIN {
	x=3
	y=2
	print "fields are",x,y
}
{
	print $x ":" $y
}

