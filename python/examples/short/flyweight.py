#!/usr/bin/python

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

b1=Button()
b2=Button()
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
