#!/bin/bash -u

#
# This is a simple array example in bash.
# we create the array the bash way
#
#	Mark Veltzer
#
# References:
# - http://tldp.org/LDP/abs/html/arrays.html

# the two next lines are both wrong
#x=(56,23,14)
#x=[56,23,14]
# the next one is a one line version to declare and initialize an array
#x=(56 23 14)
# the element by element way...
#x[0]=56
#x[1]=23
#x[2]=14

# the right way to iterate an array
for y in ${x[*]}
do
	echo $y
done

echo "size of array is ${#x[*]}"
