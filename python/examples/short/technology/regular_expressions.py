#!/usr/bin/python

"""
This is a sample for using regular expressions in python

	Mark Veltzer <mark@veltzer.net>
"""
import re # for .compile, .finditer

c=re.compile('^foobar$')

if c.match('foobar'):
	print 'yes'
