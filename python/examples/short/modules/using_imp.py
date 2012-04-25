#!/usr/bin/python

"""
Demo to show the usage of the imp module in python

	Mark Veltzer <mark@veltzer.net>
"""

from __future__ import print_function
import imp

imp.load_source('__main__','using_imp_one.py')
print('add(2,2) is',add(2,2))
imp.load_source('__main__','using_imp_two.py')
print('add(2,2) is',add(2,2))
