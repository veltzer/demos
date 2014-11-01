#!/bin/bash -u

<<COMMENT

counting numbers of calls to functions

COMMENT

# no need for this initialisation
# FUNCS=()
# CALLS=()

function find_func {
	local name=$1
	local counter=0
	for ff in ${FUNCS[*]}
	do
		if [[ $ff = $name ]]
		then
			return $counter
		fi
	done
	newloc=${#FUNCS}
	$FUNCS[$newloc]=$name
	$CALLS[$newloc]=0
	return $newloc
}

function increase_calls {
	local curr=${FUNCNAME[1]}
	find_func $curr
	local location=$?
	$CALLS[$location]++
}

function print_calls {
	echo $CALLS
}

function funca {
	increase_calls
}

function funcb {
	increase_calls
}

funca
funca
funca
funcb

print_calls
