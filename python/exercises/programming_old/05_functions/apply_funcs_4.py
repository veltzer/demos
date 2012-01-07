#!/usr/bin/python

def apply_funcs(lst, value):
	return [f(value) for f in lst]

def apply_funcs_2(lst, *args,**kwargs):
	return [f(*args,**kwargs) for f in lst]

import math
import operator
print apply_funcs([math.sin,math.cos],0.2)
print apply_funcs_2([operator.add,operator.sub],5,4)

