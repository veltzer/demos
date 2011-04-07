#!/usr/bin/python

def Person(name,age):
	data={}
	data['name']=name
	data['age']=age
	def setName(iname):
		data['name']=iname
	def getName():
		return data['name']
	def setAge(iage):
		data['age']=iage
	def getAge():
		return data['age']
	def printMe():
		print 'name',data['name']
		print 'age',data['age']
	methods={}
	methods['setName']=setName
	methods['getName']=getName
	methods['setAge']=setAge
	methods['getAge']=getAge
	methods['printMe']=printMe
	return methods

p1=Person("bilbo",111)
p1['setName']('frodo')
p1['printMe']()
p2=Person("gandalf",513)
p2['setName']('sauron')
p2['printMe']()
p1['printMe']()
