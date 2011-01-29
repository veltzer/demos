#!/bin/bash -u

clear

phonebook="phonebook.txt"

function is_there {
	grep "^$name," $phonebook > /dev/null
}

function read_name {
	echo "Please enter a name"
	read name
}
function read_phone {
	echo "Please enter a phone"
	read phone
}
function error_name_there {
	echo "name is in the phonebook" 1>&2
}
function error_name_not_there {
	echo "name is not in the phonebook" 1>&2
}

echo "=================================="
echo "=== PhoneBook version 6.7 beta ==="
echo "=================================="

if [[ -f $phonebook ]]
then
	echo "found phonebook in [$phonebook]. using it"
else
	touch $phonebook
	echo "haven't found phonebook. Created one in [$phonebook]."
fi

read

clear
PS3="please enter your selection> "
select item in insert delete search printbook printcount update exit
do
	case $item in
		insert)
			echo
			echo "in insert"
			echo "=================================="
			read_name
			if is_there
			then
				error_name_there
			else
				read_phone
				echo $name,$phone >> $phonebook
			fi
			;;
		delete)
			echo
			echo "in delete"
			echo "=================================="
			read_name
			if is_there
			then
				grep -v "^$name," $phonebook > tmp.txt
				mv tmp.txt $phonebook
			else
				error_name_not_there
			fi
			;;
		search)
			echo
			echo "in search"
			echo "=================================="
			read_name
			if is_there
			then
				grep "^$name," $phonebook | cut -f 2 -d ,
			else
				error_name_not_there
			fi
			;;
		printbook)
			echo
			echo "in printbook"
			echo "=================================="
			cat $phonebook | sort -t , | less
			;;
		printcount)
			echo
			echo "in printcount"
			echo "=================================="
			wc -l $phonebook | cut -f 1 -d " "
			;;
		update)
			echo
			echo "in update"
			echo "=================================="
			read_name
			if is_there
			then
				read_phone
				grep -v "^$name," $phonebook > tmp.txt
				mv tmp.txt $phonebook
				echo $name,$phone >> $phonebook
			else
				error_name_not_there
			fi
			;;
		exit)
			echo
			echo "in exit"
			exit 0
			;;
	esac
done
