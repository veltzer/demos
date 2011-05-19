#!/bin/ksh
if [ $# -lt 2 ]
then
	echo "Usage: power.ksh number number"
	exit 1
else
	a=$1
	b=$2
	c=1
	while [ $c -lt $b ]
	do
		let a=a*$1
		let c=c+1
	done
	echo $a
	exit 0
fi
