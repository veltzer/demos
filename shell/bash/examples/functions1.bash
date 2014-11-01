#!/bin/bash -u

<<COMMENT

examples of how to write functions in bash which accept any number
of arguments.

NOTES:
local only works in bash

COMMENT

function print_all_args {
	local x=1
	local item
	for item in "$@"
	do
		echo "argument $x is $item"
		let "x=x+1"
	done
}

function add_all_args {
	local sum=0
	for item in "$@"
	do
		let "sum+=item"
	done
	echo "sum is $sum"
}

# this functions demostrates how to create useful shortcuts
# in this case: printing messages to the console

progname=$0
function myecho {
	echo $progname $@
}

# this function return an error. Not by an explicit statement
# but rather by the fact that the return value of a function
# is the return value of the last command in the function
# (if a return statement is missing)

function generate_error {
	ls -l /foo 2> /dev/null
}

#print_all_args one 5 -8.9
#add_all_args 5 7 -4
#myecho arg1 arg2 arg3 arg4
generate_error
echo $?
