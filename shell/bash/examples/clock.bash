#!/bin/bash -u

<<COMMENT

this example shows how to create text counters using bash and the
built-in "echo -en".
if you want documentation on the built-in echo command use "man builtins"
note the spaces printed to compensate for numbers with large number of digits
turning into small number of digits.

COMMENT

let "x=100"
while [[ $x -gt 0 ]]; do
	echo -en "$x    \r"
	let "x=x-1"
	sleep 1
done
