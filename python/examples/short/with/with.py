#!/usr/bin/python

"""
This is an example of creating your own resource to be used with the "with"
python syntax.

	Mark Veltzer <mark@veltzer.net>
"""

enterCallsCounter=0
exitCallsCounter=0

class MyResource:
	""" this is the constructor (the thing we call at the "with") """
	def __init__(self,suppress=False):
		self.suppress=suppress
	""" this method will be called at the begining of the "with" block """
	def __enter__(self):
		print("doing __enter__")
		global enterCallsCounter
		enterCallsCounter+=1
		return self
	""" this method will be called at the end of the "with" block """
	def __exit__(self, type, value, traceback):
		print("doing __exit__")
		global exitCallsCounter
		exitCallsCounter+=1
		return self.suppress==True

""" first lets try to just see if enter and exit are called """
with MyResource() as r:
	print(r)
assert enterCallsCounter==1
assert exitCallsCounter==1
print("yes, if we got this far it means that both enter and exit were called exactly one time")

""" now lets try to throw an exception from the "with" block and see that exit it called """
try:
	with MyResource() as r:
		print(r)
		raise Exception("foobar")
except Exception:
	pass
assert enterCallsCounter==2
assert exitCallsCounter==2
print("yes, if we got this far it means that both enter and exit were called exactly one time")

""" now lets try to suppress the exception thrown """
with MyResource(suppress=True) as r:
	print(r)
	raise Exception("foobar")
assert enterCallsCounter==3
assert exitCallsCounter==3
print("yes, if we got this far it means that both enter and exit were called exactly one time")
