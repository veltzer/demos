#!/usr/bin/python

"""
this is an example of what typo errors could cause in python.
"""
def return_inc(val_to_increment):
	val_to_incremnt=val_to_increment+1
	return val_to_increment

print return_inc(5)
