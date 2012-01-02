#!/usr/bin/python

# for int/int to return a float
from __future__ import division

def max_min_avg(*num):
	"""Return a tuple containing the maximum, minimum and average
	of the given numbers"""
	return max(num), min(num), sum(num)/len(num)
print max_min_avg(3,4,5)
def max_min_avg_2(lst):
	"""Return a tuple containing the maximum, minimum and average
	of the given numbers"""
	return max(lst), min(lst), sum(lst)/len(lst)
print max_min_avg_2([3,4,5])
print max_min_avg_2((3,4,5))
