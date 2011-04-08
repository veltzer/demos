#!/usr/bin/python

"""
this example explores how python allocates memory.
"""

import time;

acc=[];
counter=0;
while True:
	print "counter is "+str(counter);
	counter+=1;
	l=[];
	l=range(0,1024);
	acc.append(l);
	time.sleep(2);
