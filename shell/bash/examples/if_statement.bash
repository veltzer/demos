#!/bin/bash

# this example explores the conditional statement in bash
# note that:
# - [ is a shell builtin
# - [[ is a shell keyword
# - test is a shell builtin 

if test -d ~/workspace-$VERSION -a -f ~/install/eclipse-$VERSION/eclipse; then
	~/install/eclipse-$VERSION/eclipse -data ~/workspace-$VERSION 2> /dev/null 1> /dev/null &
else
	echo "you have a problem with the version [$VERSION]..."
fi
