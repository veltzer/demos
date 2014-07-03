#!/bin/bash -u

# this example shows how to create text counters using bash and the
# built-in "echo -en".

let "x=180"; while [[ $x -gt 0 ]]; do echo -en $x "\r"; let "x=x-1" ; sleep 1; done
