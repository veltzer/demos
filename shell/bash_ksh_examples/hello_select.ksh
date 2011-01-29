#!/usr/bin/ksh -u

PS3="please select some fruit > "
select item in "a and b" "c and d"
do
	echo "item selected is $item"
done
