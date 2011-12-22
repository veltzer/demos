#!/usr/bin/python

"""
This is an example of the 'os.walk' API that allows one to traverse
a directory of files recursivly.
"""

"""
	Mark Veltzer <mark@veltzer.net>
"""
import os

for root, dirs, files in os.walk('.'):
	#print root, dirs, files
	#print root, dirs, files
	for file in files:
		#print file
		for num,line in enumerate(open(file)):
			if line.startswith("import"):
				print file, num, line 
