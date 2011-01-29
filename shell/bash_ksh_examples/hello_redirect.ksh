#!/usr/bin/ksh -u

exec 3>&1
exec 1>&-
echo "Where is this?"
exec 1>&3
echo "And this ?"
