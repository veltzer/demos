#!/usr/bin/python

from twisted.internet import reactor
import time

def aSillyBlockingMethod(t,stop):
	time.sleep(t)
	print t,"seconds have passed"
	# this will not work - we are running in a separate thread...
	#if stop:
	#	reactor.stop()

# run method in thread
reactor.callInThread(aSillyBlockingMethod, 2,True)
reactor.callInThread(aSillyBlockingMethod, 1,False)
reactor.suggestThreadPoolSize(15)
reactor.run()
