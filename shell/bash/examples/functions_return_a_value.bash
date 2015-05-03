#!/bin/bash -u

<<COMMENT

These examples show how to return a value from a bash function

NOTES:
- the 'return' statement will change $? and not the return value.
- echo seems to be the way to go.

COMMENT

function return4 {
	echo 4
}

function return4b {
	return 4
}

A=$(return4)
echo $A
A=$(return4b)
echo $?
echo $A
