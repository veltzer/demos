#!/usr/bin/ksh -u

function read_data {
	typeset +a fields pid ppid name
	ps -e --no-headers -o pid,ppid,comm | \
	while read line
	do
		fields=($line)
		pid=${fields[0]}
		ppid=${fields[1]}
		name=${fields[2]}
		pid_to_name[$pid]=$name
		pid_to_children[$ppid]="${pid_to_children[$ppid]:-} $pid"
	done
}

function print_proc {
	typeset +a num offset counter cpid ppid childrens children
	num=$1
	offset="$2"
	# get the name of the process
	name=${pid_to_name[$num]}
	# print it and increase the offset
	echo "$offset$name"
	offset=" "$offset
	for cpid in ${pid_to_children[$num]:-}
	do
		print_proc $cpid "$offset"
	done
}

read_data
print_proc ${1:-1} ""
