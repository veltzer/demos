#!/bin/bash -u

<<COMMENT

This is a script that never dies and keeps printing stuff at a low rate.
Useful for demos.

COMMENT

while [[ true ]]; do
	echo nag nag...
	sleep 1
done
