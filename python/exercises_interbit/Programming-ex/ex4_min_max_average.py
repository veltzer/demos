# for int/int to return a float
from __future__ import division

def max_min_avg(* num):
    """Return a tuple containing the maximum, minimum and average
    of the given numbers"""
    return max(num), min(num), sum(num)/len(num)
