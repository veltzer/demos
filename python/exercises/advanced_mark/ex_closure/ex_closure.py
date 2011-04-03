#!/usr/bin/python

import sys

def Person(name,age):
	data={}
	data['name']=name
	data['age']=age
	def setName(name):
		data.__setitem__('name',name)
	def getName():
		return data.__getitem__('name')
	def setAge(age):
		if age<=0:
			print 'error!!!'
			sys.exit(1)
		data.__setitem__('age',age)
	def getAge():
		return data.__getitem__('age')
	def printMe():
		print 'name is ',data['name']
		print 'age is ',data['age']
	methods={}
	methods['setName']=setName
	methods['getName']=getName
	methods['setAge']=setAge
	methods['getAge']=getAge
	methods['printMe']=printMe
	return methods

p1=Person('mark',36)
p2=Person('doron',32)
p1['printMe']()
p2['printMe']()
p1['setName']('foobar')
p1['printMe']()
p2['printMe']()
