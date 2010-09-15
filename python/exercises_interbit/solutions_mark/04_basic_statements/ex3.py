#!/usr/bin/python
orig = {"Israel":"Jerusalem","France":"Paris","Italy":"Rome","Egypt":"Cairo"}
reversed = {}
for item in orig.items():
	reversed[item[1]]=item[0]
print reversed 
