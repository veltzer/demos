#!/usr/bin/python

def my_print(x):
	print x

def call_many_times(func,times,*args,**kwargs):
	for x in xrange(times):
		func(*args,**kwargs)


call_many_times(my_print,5,"hello")
