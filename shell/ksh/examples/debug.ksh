#!/usr/bin/ksh -uxv

# notice the command line above and the flags passed to ksh for debug
# from the ksh manual:
# -u Treat unset parameters as an error when substituting.
# -v Print shell input lines as they are read.
# -x Print commands and their arguments as they are executed.

PS4='$LINENO '
echo "Hello, World!"
