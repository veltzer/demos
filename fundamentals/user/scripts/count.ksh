 #!/bin/ksh
a=0
until [ $a -gt 9 ]
do
	let a=a+1
	echo $a
done
