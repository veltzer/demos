#!/usr/bin/awk -f
#!/home/veltzer/shell_ksh/examples/awk/invoker.pl awk,/etc/passwd,/etc/group

# program to find out the average percentage of people who use
# bash on your system

# maintainer documentation
# uname_to_shell indexed by person name and holds the shell
# sum_for_group indexed by group name and holds number of
#	users in the group that use bash
# persons_for_group indexed by group name and holds number of
#	total users in the group.

BEGIN {
	FS=":"
}
{
	if(FILENAME~/passwd/) {
		userid=$1
		pgid=$4
		shell=$7

		uname_to_shell[userid]=shell
		gid_users[pgid]++
		if(shell~/bash/)
			bash_users[pgid]++
	}
	if(FILENAME~/group/) {
		group=$1
		pgroup=$3
		users=$4

		persons_for_group[group]+=split(users,arr,",")
		for(iperson in arr) {
			person=arr[iperson]
			if(uname_to_shell[person]~/bash/) {
				sum_for_group[group]++
			}
		}
		persons_for_group[group]+=gid_users[pgroup];
		sum_for_group[group]+=bash_users[pgroup];
	}
}
END {
	sum=0
	counter=0
	for(group in persons_for_group) {
		if(persons_for_group[group]>0) {
			sum+=sum_for_group[group]/persons_for_group[group]
			print sum_for_group[group],persons_for_group[group]
			counter++
		}
	}
	print "number of real groups",counter
	print "group average bash usage for your system is",sum/counter
}
DONE
