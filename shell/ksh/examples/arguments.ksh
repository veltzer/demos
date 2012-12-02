#!/usr/bin/ksh -u

echo "number of arguments is $#"
for arg in "$@"
do
	echo $arg
done
