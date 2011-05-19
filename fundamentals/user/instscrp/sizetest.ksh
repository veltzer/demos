#!/bin/ksh
if [ X$1 = X -o X$2 = X ]
then
	echo "Usage: sizetest.ksh file1 file2"
	exit 1
elif [ $1 = $2 ]
then
	echo "You have inserted the same file for comparison"
	exit 1
elif [ -f $1 -a -f $2 ]
then
	FSIZE1=`ls -l $1 | tr -s "	 " " " | cut -d" " -f5`
	FSIZE2=`ls -l $2 | tr -s "       " " " | cut -d" " -f5`
	if [ $FSIZE1 -gt $FSIZE2 ]
	then
		echo "File $1 is bigger then file $2"
		exit 0
	elif [ $FSIZE1 -lt $FSIZE2 ]
	then
		echo "File $2 is bigger then file $1"
		exit 0
	else
		echo "File $1 and File $2 are of the same size"
		exit 0
	fi
else
	echo "Please check that file $1 and File $2 exists and that they are not Directories"
	exit 1
fi
