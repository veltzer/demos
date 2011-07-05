#!/usr/bin/python

"""
	Mark Veltzer <mark@veltzer.net>
"""
class MyClass:
	pubvar=5
	__privar=7
	def pr(self):
		print 'printing'
		print 'pubvar is '+str(self.pubvar)
		print 'privar is '+str(self.__privar)
	# this demonstrates that you don't have to call 'self' as 'self', it's just
	# a convention
	def setPrivate(s,val):
		s.__privar=val
	def setPublic(s,val):
		s.pubvar=val

print 'constructing'
b=MyClass()
print 'printing the newly constructed class'
b.pr()
print 'setting accessible variable'
b.pubvar=4
b.pr()
print 'and with a method'
b.setPublic(9)
b.pr()
print 'and another way to call the same method'
MyClass.setPublic(b,11)
b.pr()
print 'here is how you access the accessible variable '+str(b.pubvar)
print 'setting inaccessible variable'
b.__privar=6;
b.pr()
print 'didnt work, huh ?'
print 'from outside its value looks like '+str(b.__privar)
print 'this is our real var '+str(b._MyClass__privar);
print 'this means that there is no real security for the __[var] in python'
print 'lets look at our class'
print dir(MyClass)
print dir(MyClass.__module__)
