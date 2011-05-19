#!/bin/ksh
if [ X$1 = X ]
then
	echo "Usage: country3.ksh country name"
	exit 1
else
	if grep -i $1 country.txt > /dev/null
	then
		echo "which information would you like to get:"
		echo "1) Population"
		echo "2) Capital City Name"
		read OPTION
		if [ X$OPTION = X1 ]
		then
			POPU=`grep -i $1 country.txt | cut -d, -f2`
			echo "$1 Population is: $POPU"
			exit 0
		elif [ X$OPTION = X2 ]
		then
			CCITY=`grep -i $1 country.txt | cut -d, -f3`	
			echo "$1 Capital City is: $CCITY"
			exit 0
		else
			echo "You should choose either 1 or 2"
			exit 1
		fi
	else
		echo "The Country is not in our database"
		exit 2
	fi
fi
