#!/bin/bash -u

# this script shows how to break in the middle of a for loop
# in case anything goes wrong.
# give it a list of filenames that exist and some that dont

for x in $@
do
	echo "doing $x"
	stat "$x" 2> /dev/null 1> /dev/null
	if [[ $? -ne 0 ]]; then
		exit $$
	fi
done
