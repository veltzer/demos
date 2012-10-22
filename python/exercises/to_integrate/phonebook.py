#!/usr/bin/python

"""
This is a phonebook app
"""

phonebook={}
phonebook["mark"]=("veltzer","050-5665636")
phonebook["shay"]=("sarid","054-4506093")

while True:
	print "Please select operation:"
	print "1) get family name by name"
	print "2) get phone by name"
	op=int(raw_input("Give me a name "))
	if op==1:
		name=raw_input("Give me a name ")
		print phonebook[name][0]
	if op==2:
		name=raw_input("Give me a name ")
		print phonebook[name][1]
