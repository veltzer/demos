#!/usr/bin/python

from threading import Thread

# this function was written with NO consideratiion of threading...
def func(data):
	print data

# this is a wrapper that creates a function with no argument
# that runs any other function with arguments
def create_func(func_to_run,data):
	def wrapper():
		func_to_run(data)
	return wrapper

myfunc=create_func(func,"Hello,World")
t=Thread(target=myfunc)
t.start()
t.join()
