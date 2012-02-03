#!/usr/bin/ksh -u

# include the functions

base=`dirname $0`

function include_functions {
	relative_name=$1
	source $base/$relativ_name
}
include_functions myfunctions.ksh
# include the functions (notice that the functions file need
# not be executable)
#source $base/myfunctions.ksh

# now use the functions

foo
bar;bar
