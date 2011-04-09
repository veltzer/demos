#!/usr/bin/python

"""
	Mark Veltzer <mark@veltzer.net>
"""
class Button:
	color="blue"
	text="no text"
	weight=3
	bgColor=7
	def __init__(self):
		# look Ma! nothing in init...
		pass
	def doSomething(self):
		print "doing something with color ",self.color
		print "doing something with text ",self.text
	def setText(self,text):
		self.text=text
	def setColor(self,color):
		self.color=color
	def getColor(self):
		if 'color' in self.__dict__:
			return self.color
		else:
			return Button.color
	def getWeight(self):
		return self.weight

b1=Button()
b2=Button()
print Button.color
print b1.getColor()
print b1.weight
print b1.getWeight()
b1.setColor('white')
b2.setColor('black')
b1.doSomething()
b2.doSomething()
Button.text="new text"
b1.doSomething()
b2.doSomething()
print b1.__dict__
print b2.__dict__
#print dir(b)
