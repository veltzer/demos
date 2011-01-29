#!/usr/bin/ksh -u

ls -l
result=$?
echo
find
if [[ $result ]]
then

exit 5
