#!/usr/bin/python
d1 = {"Israel":"Jerusalem","France":"Paris","Italy":"Rome","Egypt":"Cairo"}
d2 = {}
for item in d1.items():
	key=item[0]
	value=item[1]
	d2[value]=key
print d2
