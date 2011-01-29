#!/usr/bin/ksh -u

numsigs=0

function sighandler {
	let "numsigs++"
	echo "inside signal handler $numsigs"
}

trap sighandler USR1 USR2 TERM INT
# print the status
#trap -p

while true
do
	sleep 3600
done
