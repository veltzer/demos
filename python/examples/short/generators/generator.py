#!/usr/bin/python

#input_list=range(100000)
def input_list():
	counter=0
	while True:
		yield counter
		counter+=1
		if counter==10:
			break
result=[]
for x in input_list():
	result.append(x**2)
print result
