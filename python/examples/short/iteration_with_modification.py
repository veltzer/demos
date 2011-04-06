#!/usr/bin/python

l=range(10)

for x in l:
	if x==4:
		l.pop(3)
	if x==6:
		l.insert(2,11)
