#!/bin/bash

# this is a test for the pipe device

cat /dev/mypipe0 &
let x=0
while [[ $x -lt 100 ]]; do
	cat *.c > /dev/mypipe0
	let "x=x+1"
done
