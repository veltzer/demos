#!/usr/bin/ksh -u

function read_data {
	typeset +a fields pid ppid name line
	pidnum=0
	ps -e --no-headers -o pid,ppid,comm | \
	while read line
	do
		fields=($line)
		pid=${fields[0]}
		ppid=${fields[1]}
		name=${fields[2]}
		pid_to_name[$pid]=$name
		pid_to_ppid[$pid]=$ppid
		index_to_pid[$pidnum]=$pid
		let "pidnum++"
	done
}

function print_proc {
	typeset +a num offset counter cpid ppid
	num=$1
	offset="$2"
	# get the name of the process
	name=${pid_to_name[$num]}
	# print it and increase the offset
	echo "$offset|-$name"
	offset="  "$offset
	counter=0
	while [[ $counter -lt $pidnum ]]
	do
		cpid=${index_to_pid[$counter]}
		ppid=${pid_to_ppid[$cpid]}
		if [[ $ppid -eq $num ]] 
		then
			print_proc $cpid "$offset"
		fi
		let "counter++"
	done
}

read_data
print_proc 1 ""
