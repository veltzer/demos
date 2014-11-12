#!/bin/bash -u

<<COMMENT

This is an example of how the pid of a certain process/thread in bash.
Note that you don't want to use the standard ps output

NOTES:
- you dont want to run ps with the -o flag because it shows the PID on
the first column and to align it it actually pads it with spaces but
not in all lines which creates lots of problems for tr and cut later on.
- you want to be careful on the grep: if you grep just for the name (say 'init')
you will get every process that has 'init' somewhere in its name.
- you have to double careful with grep because it will return itself if
you tell ps to show the 'cmd' field which is the full command line. thats
why you can do 'grep foo | grep -v grep', or better yet, use the 'comm'
field which is the command, not full path, without arguments.
- next to be careful on the grep we use the enhanced grep regular expressions
with ^ (for begining of line), and a trailing space to make sure we get
our executable or thread.
- the rest is just tr(1) for compression of spaces and cut to cut the right field
out.
- if you are on an embedded system you mileage may vary since some of the
following commands may not have all the flags since they are reduced version.
Play around with it until you find the right flags.

COMMENT

# best
pid1=`ps -e -L -o comm,pid | grep "^init " | tr -s " " | cut -d " " -f 2`
# not the best
pid2=`ps -e -L -o cmd,pid | grep "^/sbin/init " | tr -s " " | cut -d " " -f 2`
# flakey
pid3=`ps -e -L -o cmd,pid | grep "/sbin/init" | grep -v grep | tr -s " " | cut -d " " -f 2`
# real flakey
pid4=`ps -eL | grep " init$" | tr -s " " | cut -d " " -f 3`
# really really flakey
pid5=`ps -eL | grep " init" | tr -s " " | cut -d " " -f 3`
# absolutely nuts
pid6=`ps -eL | grep " init" | grep -v grep | tr -s " " | cut -d " " -f 3`

echo "pid1 is $pid1"
echo "pid2 is $pid2"
echo "pid3 is $pid3"
echo "pid4 is $pid4"
echo "pid5 is $pid5"
echo "pid6 is $pid6"
