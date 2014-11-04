#!/bin/bash -u

<<COMMENT

An attempt to make a zombie in bash
This is not possible. Why? because bash installs
signal hander for SIGCHLD and collects the signals for dead
children.
This could cause problems since the PID may be reused by the OS.
which means that if you are unlucky and many processes are launched
you may launch many processes some of which may end while you are
still in the business of launching and so some of your processes
will launch with the same PID which will be a problem since
you will not be able to distinguish between them...:(
You can see all of this by strace(1) of this script.

COMMENT

echo "running process in the background..."
sleep 5 &
PID=$!
echo "the pid is [$PID]..."

let "x=0"
while [[ true ]]; do
	let "x=x+1"
done
