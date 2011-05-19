#!/bin/ksh
echo "Phone book script written by Dedi Enbal"
echo "What information would you like to get:"
echo "1) Phone Number"
echo "2) Adress"
echo "Please Choose one of the above:"
read CHOICE
if [ X$CHOICE = X1 ]
then
	echo "Please enter a Name:"
	read NAME
	if [ "X$NAME" = X ]
	then
		echo "You did not enter any name"
		exit 1
	elif cut -d: -f1 addressbook.txt | grep -i "$NAME" > /dev/null
	then
		echo "\n*******************"
		echo "Search results are:"
		echo "*******************\n"
		grep -i "$NAME" addressbook.txt | cut -d: -f1,2 
		exit 0
	else
		echo "No Such name in our database."
		exit 2
	fi
elif [ X$CHOICE = X2 ]
then
	echo "Please enter a Name:"
	read LNAME
	if [ "X$LNAME" = X ]
        then
                echo "You did not enter any name"
                exit 1
        elif cut -d: -f1 addressbook.txt | grep -i "$LNAME" > /dev/null
        then
		echo "\n*******************"
		echo "Search results are:"
		echo "*******************\n"
		grep -i "$LNAME" addressbook.txt | cut -d: -f1,3
                exit 0
        else
                echo "No Such name in our database."
                exit 2
        fi
else
	echo "Please choose either 1 or 2"
	exit 1
fi
 
