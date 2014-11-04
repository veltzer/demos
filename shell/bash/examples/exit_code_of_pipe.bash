#!/bin/bash -u

<<COMMENT

This is an example that shows that the exit code of a shell pipe
is the exit code *of the last component* of the pipe.

COMMENT

true | false
echo "01: $?"
false | true
echo "10: $?"

for x1 in true,0 false,1
do
	x1s=(${x1//,/ })
	for x2 in true,0 false,1
	do
		x2s=(${x2//,/ })
		for x3 in true,0 false,1
		do
			x3s=(${x3//,/ })
			${x1s[0]} | ${x2s[0]} | ${x3s[0]}
			res=$?
			echo "${x1s[1]}${x2s[1]}${x3s[1]} -> $res" 
		done
	done
done
