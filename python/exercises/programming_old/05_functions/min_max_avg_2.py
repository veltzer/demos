#!/usr/bin/python

def my_min(list):
	min=list[0]
	for x in list[1,]:
		if x<min:
			min=x
	return min

def my_max(list):
	max=list[0]
	for x in list[1,]:
		if x>max:
			max=x
	return max

def my_sum(list):
	sum=0
	for x in list[1,]:
		sum+=x
	return sum 

def min_max_avg(list):
	return my_min(list),my_max(list),float(my_sum(list))/len(list)

l=range(0,100000)
print min_max_avg(l)
