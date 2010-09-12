#!/usr/bin/python

def foo():
	print "hello"

def foo(a):
	print "hello",a

try:
	foo()
except TypeError:
	print "oops, got an error"
foo("mark")
