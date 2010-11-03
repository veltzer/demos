#!/usr/bin/python
d1 = {"Israel":"Jerusalem","France":"Paris","Italy":"Rome","Egypt":"Cairo"}
d2 = {}
for key,value in d1.items():
	d2[value]=key
print d2
