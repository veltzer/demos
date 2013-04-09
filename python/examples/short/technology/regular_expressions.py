#!/usr/bin/python

"""
This is a sample for using regular expressions in python

	Mark Veltzer <mark@veltzer.net>
"""
import re # for .compile,.finditer

c=re.compile('^foobar (\d+)$')

# lets get the match object
m=c.match('foobar 17')
if m:
	print('m.group() is ',m.group())
	print('m.group(1) is ',m.group(1))
else:
	print('no match')
