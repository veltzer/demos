#!/usr/bin/python

"""
This is an example of how to debug a python application
just run this application and you will enter debug mode
as soon as you start the trace...
"""

"""
	Mark Veltzer <mark@veltzer.net>
"""

def calc():
	i=0
	sum=0
	while True:
		sum=sum+i
		i=i+1

if __name__ == "__main__":
	calc()
