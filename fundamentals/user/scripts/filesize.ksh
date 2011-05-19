#!/bin/ksh
if [ $# -eq 0 ]
then
	echo "Usage: filesize.ksh directory"
elif [ -d $1 ]
then
	for FILE in $1/*
	do
	if [ -f $FILE ]
	then
		SIZE=`ls -l $FILE | tr -s "	 " " " | cut -d" " -f5`
		echo "$FILE 	$SIZE"
	fi
	done
else
	echo "The file you had specified is not a directory"
fi 
