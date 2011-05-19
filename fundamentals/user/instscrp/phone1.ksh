#!/bin/ksh
echo "Phone book script written by Dedi Enbal"
echo "What information would you like to get:"
echo "1) Phone Number"
echo "2) Adress"
echo "3) Add a new entry"
echo "4) Delete an Entry"
echo "Please Choose one of the above:"
read CHOICE
case $CHOICE in
	1) echo "Please enter a Name:"
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
	   ;;	
	2) echo "Please enter a Name:"
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
	   ;;	
	3) echo "Please enter Full Name to Add:"
	   read LNAME
           if [ "X$LNAME" = X ]
           then
                echo "You did not enter any name"
                exit 1
           else
		echo "Please Enter Phone Number:"
		read NUM
		echo "Please Enter Full Address:"
		read ADD
		echo "$LNAME:$NUM:$ADD" >> addressbook.txt
		echo "The Following entry had been added:"
		echo "$LNAME $NUM $ADD"
		exit 0
	   fi
	   ;;
	4) echo "Please enter a name to delete:"
	   read LNAME
           if [ "X$LNAME" = X ]
           then
                echo "You did not enter any name"
                exit 1
           elif cut -d: -f1 addressbook.txt | grep -i "$LNAME" > /dev/null
	   then
		echo "The Following Entries would be deleted:"
	        cut -d: -f1 addressbook.txt | grep -i "$LNAME"
		echo "Do you Which to Continue ?"
		echo "1) Yes"
		echo "2) No"
		read CHOOCE
		case $CHOOCE in
			1) grep -iv "$LNAME" addressbook.txt > addressbook.new
			   mv addressbook.new addressbook.txt
			   echo "Entry had been deleted"
			   exit 0
			   ;;
			2) echo "No change had been done"
			   exit 0
			   ;;
			*) echo "Wrong option, please choose either 1 or 2"
			   exit 1
			   ;;
		esac
	   fi	
       	   ;;
	*) echo "Please choose either 1 or 2"
	   exit 1
	   ;;
esac
 
