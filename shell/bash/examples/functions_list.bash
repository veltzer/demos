#!/bin/bash -u

<<COMMENT

This example shows how to list all functions which have been declared.

COMMENT

function myfunc1 {
	echo myfunc1
}

function myfunc2 {
	echo myfunc2
}

declare -f | grep myfunc
declare -F | grep myfunc
typeset -f | grep myfunc
typeset -F | grep myfunc
compgen -A function myfunc
