#!/usr/bin/python

pb={}
for line in open("phonebook.txt"):
	line=line.rstrip("\n")
	mylist=line.split(",")
	pb[mylist[0]]=mylist[1]
print pb
