#!/bin/bash -u

#x=(56,23,14)
x[0]=56
x[1]=23
x[2]=14

# the right way to iterate an array
for y in ${x[*]}
do
	echo $y
done

echo "size of array is ${#x[*]}"
