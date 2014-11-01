#!/bin/bash

<<COMMENT

this example explores the conditional statement in bash
note that:
- [ is a shell builtin
- [[ is a shell keyword
- test is a shell builtin 

COMMENT

if test -d /etc -a -f /etc/passwd; then
	echo "/etc is a folder and /etc/passwd is a file..."
else
	echo "strange Linux system you have here..."
fi
