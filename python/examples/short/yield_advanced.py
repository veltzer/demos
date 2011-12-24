#!/usr/bin/python

"""
This is a time sharing example...

	Mark Veltzer <mark@veltzer.net>
"""

def evens():
	for x in xrange(0,100,2):
		print 'evens say ',x
		yield
def odds():
	for x in xrange(10001,10101,2):
		print 'odds say ',x
		yield

c1=evens()
c1.next()
c2=odds()
c2.next()
for x in xrange(10):
	c1.next()
	c2.next()
