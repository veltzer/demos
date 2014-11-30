#!/bin/ksh
if [ X$1 = X ]
then
	echo "Usage: country4.ksh country_name"
	exit 1
elif cut -d, -f1 country.txt | grep -i $1 > /dev/null
then
	echo "What informtaion would you like to know about $1 ?"
	echo "1) Population"
	echo "2) Capital City"
	echo "3) Date of Indipendance / Founding"
	echo "4) Continental Area"
	read CHOICE
	case $CHOICE in
		1) POPU=`grep -i $1 country.txt | cut -d, -f2`
		   echo "$1 Population is: $POPU"
		   exit 0
		   ;;
		2) CCITY=`grep -i $1 country.txt | cut -d, -f3`
		   echo "$1 capital city is: $CCITY"
		   exit 0
		   ;;
		3) IDATE=`grep -i $1 country.txt | cut -d, -f6`
		   echo "$1 date of independance / founding  is: $IDATE"
		   exit 0
		   ;;
		4) GEO=`grep -i $1 country.txt | cut -d, -f5`
		   echo "$1 Continental Area is: $GEO"
		   exit 0
		   ;;
		*) echo "Wrong Option, Please try again"
		   exit 1
		   ;;
	esac
else
	echo "No such country in database"
	exit 1
fi
