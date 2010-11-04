#!/usr/bin/python
s = raw_input("Please enter a line of digits: ")
l = [0]*10
for d in s:
	if d.isdigit():
		l[int(d)]+=1
print l
