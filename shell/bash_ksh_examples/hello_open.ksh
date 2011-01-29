#!/usr/bin/ksh -u

exec 1> some_file.txt
echo "foo"
echo "foo2"
echo "foo3"
exec 1> other_file.txt
echo "foo"
echo "foo2"
echo "foo3"
