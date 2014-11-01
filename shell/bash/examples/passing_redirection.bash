#!/bin/bash -u

<<COMMENT

This example shows that you cannot pass shell special notation (like redirection
or the like) via variables.
That is the reason for the difference in the output between the next two lines...

COMMENT

REDIRECT="> /dev/null"
ls $REDIRECT
ls > /dev/null # the output from this is not shown
