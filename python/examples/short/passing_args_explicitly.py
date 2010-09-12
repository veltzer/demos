#!/usr/bin/python

from threading import Thread

# this function was written with NO consideratiion of threading...
def func(data):
	print data

t=Thread(target=func,args=("Hello",))
t.start()
t.join()
