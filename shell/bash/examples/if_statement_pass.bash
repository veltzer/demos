#!/bin/bash

# this example shows the equivalent of a 'pass' statement
# in bash. This is the true shell builtin which executes really
# quickly and succeeds.

if [[ $PATH = "foo" ]]
then
	true
else
	echo "im in here"
	true
fi
