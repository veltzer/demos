#!/bin/bash

<<COMMENT

this example shows the equivalent of a 'pass' statement
in bash. This is the 'true' shell builtin which executes really
quickly and succeeds.

References:
http://stackoverflow.com/questions/2421586/what-is-the-bash-equivalent-of-pythons-pass-statement

COMMENT

if [[ $PATH = "foo" ]]
then
	true
else
	echo "im in here"
fi
