#!/bin/bash -u

<<COMMENT

This example shows a simple for loop over a list of elements known
in advance.

COMMENT

for j in {1..3}
do
	echo $j
done

for j in {3..1}
do
	echo $j
done

for j in {-3..-1}
do
	echo $j
done

for j in {-1..-3}
do
	echo $j
done

# you can also have a jump interval
for j in {1..7..2}
do
	echo $j
done

for j in {7..1..-2}
do
	echo $j
done
