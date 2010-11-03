#!/usr/bin/python

d1 = {"Israel":"Jerusalem","France":"Paris","Italy":"Rome","Egypt":"Cairo"}
d2 = {}
for key in d1.keys():
    d2[d1[key]] = key

print d2
