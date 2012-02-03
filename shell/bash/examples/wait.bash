#!/bin/bash -u

# An example of using the bash 'wait' function to wait for background processes
# to terminate...
# conclusions: wait waits for ALL background processes to end
#
#	Mark Veltzer

echo "running sleep 5 in the background..."
sleep 5 &
PID5=$!
echo "running sleep 10 in the background..."
sleep 10 &
PID10=$!
echo "going to wait for sleep 5..."
wait $PID5
echo "yes, 5 is done"
echo "going to wait for sleep 10..."
wait $PID10
echo "done waiting..."
