#!/bin/bash -u

<<COMMENT

This is a simple array example in bash.
we create the array the bash way

References:
- http://tldp.org/LDP/abs/html/arrays.html

COMMENT

# the two next lines are both wrong
#x=(56,23,14)
#x=[56,23,14]
# the next one is a one line version to declare and initialize an array
y=(12 94 54)
# the element by element way...
z[0]="one two"
z[1]="five six"
z[2]="three four"

# the right way to iterate an array
for elem in "${y[@]}"
#for elem in "${y[*]}"
do
	echo $elem
done
echo "size of the array is ${#y[@]}"
for elem in "${z[@]}"
do
	echo $elem
done
echo "size of the array is ${#z[@]}"

echo
echo "this is the WRONG way to do it! (gives all as one element)"
echo ===============================
for elem in "${z[*]}"
do
	echo $elem
done

echo
echo "another WRONG way to do it! (breaks down spaces)"
echo ===============================
for elem in ${z[*]}
do
	echo $elem
done

echo
echo "another WRONG way to do it! (only covers first element and breaks it down)"
echo ===============================
for elem in $z
do
	echo $elem
done

echo
echo "another WRONG way to do it! (only covers first element)"
echo ===============================
for elem in "$z"
do
	echo $elem
done
