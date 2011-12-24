#!/usr/bin/python

"""
This example iterates two containers at the same time...

	Mark Veltzer <mark@veltzer.net>
"""

ll1=[1,2,3,4,5]
ll2=[10,11,12,13,14]
ll3=[20,21,22,23,24]

for (l1,l2,l3) in zip(ll1,ll2,ll3):
	print l1,l2,l3
