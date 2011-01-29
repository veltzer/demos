#!/usr/bin/ksh -u

# set up a nice message for debugging

PS4='$LINENO '

echo "Start"

# light up debug
set -x

echo "Code in debug area 1"
echo "Code in debug area 2"
echo "Code in debug area 3"

# turn off debug
set +x

echo "End"
