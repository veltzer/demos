#!/usr/bin/python

from __future__ import print_function

"""
Example code for raising an exception,catching it and executing finally code in python.

	Mark Veltzer <mark@veltzer.net>
"""

try:
	raise ValueError("hello")
# this next line catches all exceptions, logs and throws them back...
except Exception,e:
	print("in except",e)
	raise e
finally:
	print("finally is here")
