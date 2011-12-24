#!/usr/bin/python

"""
This is an example of the 'os.walk' API that allows one to traverse
a directory of files recursivly.
This is used to implement find(1)+grep(1) in just a few lines of python.

	Mark Veltzer <mark@veltzer.net>
"""
import os # for .path.join
import re # for .compile, .finditer
import sys # for .argv, .walk

c=re.compile(sys.argv[1])

for root, dirs, files in os.walk('.'):
	for file in files:
		full=os.path.join(root,file)
		for num,line in enumerate(open(full)):
			line=line[:-1]
			for x in c.finditer(line):
				print "%s,%s: %s" % (full,num,line)
