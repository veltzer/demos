#!/bin/ksh
if [ $# -eq 0 ]
then
	echo "Usage: country5.ksh continent"
	exit 1
else
	a=0
	for CONT in `cut -d, -f5 country.txt | grep -i $1`
	do 
		let a=a+1
	done
	echo "There are $a countries in $1 continent"
fi	
