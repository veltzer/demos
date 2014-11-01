#!/bin/bash -u

<<COMMENT

An example of using the bash 'wait' function to wait for background processes
to terminate...

COMMENT

echo "running sleep 5 in the background..."
sleep 5 &
PID5=$!
echo "the pid of 5 is $PID5..."
echo "running sleep 10 in the background..."
sleep 10 &
PID10=$!
echo "the pid of 10 is $PID10..."
echo "going to wait for sleep 5..."
wait $PID5
echo "yes, 5 is done and status is $?"
echo "going to wait for sleep 10..."
wait $PID10
echo "yes, 10 is done and status is $?"
echo "done waiting..."
