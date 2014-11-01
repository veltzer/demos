#!/bin/bash

<<COMMENT

this is the sequel to the previous example but this time running without the -u...
as you can see the script does not stop and no error is printed.
conclusion: better run with -u.

COMMENT

somevar=7
echo $someva
echo "hi"
