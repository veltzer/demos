#!/usr/bin/python

import os
import signal

h=signal.getsignal(signal.SIGUSR1)

def myhandler(signum,frame):
	#h(signum,frame)
	print "Here I am!"

signal.signal(signal.SIGUSR1,myhandler)

while True:
	print "Going to pause()..."
	signal.pause()
