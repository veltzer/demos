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
y=(12 94 54)
# the element by element way...
z[0]=56
z[1]=23
z[2]=14

# the right way to iterate an array
for elem in ${y[*]}
do
	echo $elem
done
echo "size of the array is ${#y[*]}"
for elem in ${z[*]}
do
	echo $elem
done
echo "size of the array is ${#z[*]}"
