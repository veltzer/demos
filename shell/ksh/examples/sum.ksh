#!/usr/bin/ksh

function sumall {
	typeset +a sum=0
	typeset +a x
	for x in "$@"
	do
		let "sum+=x"
	done
	echo "sum is $sum"
}

sumall 2 3 4 5
sumall
sumall 2 2
