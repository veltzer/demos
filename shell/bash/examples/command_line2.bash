#!/bin/bash -u

<<COMMENT

this is an example of getting specific arguments from the command line.

COMMENT

if [[ $# -ne 2 ]]; then
	echo "you must pass exactly two arguments to this script..."
	exit 1
fi
echo "the name of the script is $0"
echo "first argument is $1"
echo "second argument is $2"
