#!/bin/bash -u

<<COMMENT

this script shows how to break in the middle of a for loop and use continue to return to the loop

COMMENT

for x in {1..10}
do
	echo $x
	if [[ $x -eq 5 ]]
	then
		echo "yes, x is 5"
		break
	fi
done

for x in {1..10}
do
	if [[ $(( $x % 2 )) -eq 1 ]]
	then
		continue
	fi
	echo $x
done
