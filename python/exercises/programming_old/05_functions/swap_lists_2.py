#!/usr/bin/python

def swap_lists(l1,l2):
	for i in xrange(len(l1)):
		l1[i],l2[i] = l2[i],l1[i]

l1=[1,2,3]
l2=[4,5,6]
swap_lists(l1,l2);
print l1;
print l2;
