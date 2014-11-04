#!/bin/bash -u

<<COMMENT

This example shows a simple for loop over a list of elements known
in advance.

COMMENT

for x in red yellow green
do
	echo $x
done
