#!/usr/bin/ksh -u

# this is a solution to the phonebook exercise, WITHOUT using
# functions

version="myphones beta gold 7.6"

clear
printf "==== %40s ====\n" " "
printf "==== %40s ====\n" "$version"
printf "==== %40s ====\n" " "
read
clear

phonebook="phonebook.txt"

if [[ -f $phonebook ]]
then
	echo "found phonebook in [$phonebook]. using that"
else
	echo "didn't find phonebook. creating one"
	touch $phonebook
fi

options="insert delete update find showbook showcount exit"
PS3="$version > "
select item in $options
do
	case $item in
		insert)
			echo "in $item"
			echo -n "Please enter a name: "
			read name
			if grep "^$name," $phonebook > /dev/null
			then
				echo "name $name is already in the phonebook"
			else
				echo -n "Please enter a number: "
				read number
				echo $name,$number >> $phonebook
			fi
		;;
		delete)
			echo "in $item"
			echo -n "Please enter a name: "
			read name
			if grep "^$name," $phonebook > /dev/null
			then
				grep -v "^$name," $phonebook > tmp.txt
				mv tmp.txt $phonebook
			else
				echo "name $name is not in phonebook"
			fi
		;;
		update)
			echo "in $item"
			echo -n "Please enter a name: "
			read name
			if grep "^$name," $phonebook > /dev/null
			then
				echo -n "Please enter a number: "
				read number
				grep -v "^$name," $phonebook > tmp.txt
				mv tmp.txt $phonebook
				echo $name,$number >> $phonebook
			else
				echo "name $name is not in phonebook"
			fi
		;;
		find)
			echo "in $item"
			echo -n "Please enter a name: "
			read name
			if grep "^$name," $phonebook > /dev/null
			then
				grep "^$name," $phonebook | cut -d , -f 2
			else
				echo "name $name is not in phonebook"
			fi
		;;
		showbook)
			echo "in $item"
			cat $phonebook
		;;
		showcount)
			echo "in $item"
			wc -l $phonebook
		;;
		exit)
			echo "in $item"
			exit 0
		;;
	esac
done
