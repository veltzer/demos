#!/bin/ksh
if [ $# -eq 0 ]
then 
	echo "Usage: largefiles.ksh direcoty"
elif [ -d $1 ]
then
	for FILES in $1/*
	do
		if [ -f $FILES ]
		then
			FSIZE=`ls -l $FILES | tr -s "	 " " " | cut -d" " -f5`
			if [ $FSIZE -gt 1024 ]
			then
			echo $FILES
			fi
		fi
	done
else
	echo "Please check that $1 is a Dierctory"
fi
