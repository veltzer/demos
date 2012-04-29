#!/usr/bin/python

# this solution is the "varargs" type. You can call the min_max_avg function in any
# of the following ways:
# (my_max,my_min,my_avg)=max_min_avg(1,2,3)
# (my_max,my_min,my_avg)=max_min_avg(1,2,3,4,5,6)
# (my_max,my_min,my_avg)=max_min_avg(*range(100000))

def max_min_avg(*num_list):
	"""return a tuple containing the maximnum_list, minimum and avrage of the given num_listbrs"""
	#print num_list
	return max(num_list), min(num_list), sum(num_list)/len(num_list)

print max_min_avg(1,2,3,4,5);
print max_min_avg(*xrange(1000));
