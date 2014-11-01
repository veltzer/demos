#!/bin/bash -u

<<COMMENT

example of showing all command line arguments using a for loop

COMMENT

echo "lengh of command line is [$#]"
for x in $@
do
	echo $x
done
