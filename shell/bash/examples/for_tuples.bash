#!/bin/bash -u

<<COMMENT

This example shows a simple for loop over a list of elements known
in advance.

COMMENT

for x in red,4 yellow,5 green,6
do
	set ${x//,/ }
	echo $1 $2
done

for x in apple,green strawberry,red banana,yellow
do
	xsplit=(${x//,/ })
	echo "${xsplit[0]}s are ${xsplit[1]}"
done
