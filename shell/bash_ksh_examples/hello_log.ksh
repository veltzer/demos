#!/usr/bin/ksh -u

let x=0
while [[ $x -lt 100 ]]
do
	logger -t $0 "this is a message to syslog $x"
	let "x=x+1"
done
