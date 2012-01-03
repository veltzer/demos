#!/usr/bin/python

counter=0
def give_me_a_unique_value():
	global counter
	counter+=1
	return counter

print give_me_a_unique_value()
print give_me_a_unique_value()
print give_me_a_unique_value()

def give_me_unique_generator():
	l=[0]
	def give_me_unique():
		l[0]+=1
		return l[0]
	return give_me_unique
g=give_me_unique_generator()
print g()
print g()
print g()
print g()
