#!/usr/bin/python

import unittest

class myunittest(unittest.TestCase):
	def testThis(self):
		sum=0
		for x in xrange(0,100):
			sum+=x;
		self.assert_(sum==4950)

if __name__=='__main__':
	unittest.main()
