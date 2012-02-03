#!/bin/bash -u

function print_stack {
	local x=0
	for curr in ${FUNCNAME[*]}
	do
		# do not print_stack itself
		if [[ $x != 0 ]]
		then
			local line=${BASH_LINENO[$x-1]}
			local source=${BASH_SOURCE[$x-1]}
			echo $curr,$line,$source
		fi
		let "x=x+1"
	done
}

function inner {
	print_stack
}

function outer {
	inner
}

outer
