#!/bin/csh

# This example shows a simple if statement in csh
# You CANNOT write a csh if/then statement in a single line (stupid, I know!)

if ( $USER == 'mark' ) then
	echo 'hello, master...'
else
	echo 'I dont know you...'
endif
