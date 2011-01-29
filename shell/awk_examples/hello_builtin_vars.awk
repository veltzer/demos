#!/usr/bin/awk -f
BEGIN {
	FS=":"
}
{
	print NF,NR,$0
}

