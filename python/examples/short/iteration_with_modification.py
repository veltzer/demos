#!/usr/bin/python

"""
The purpose of this example is to show what happens when we modify elements
while iterating. What is the moral of this example? DONT DO IT. Do not modify
data structures while you are iterating them. Mind you that python, unlike
other languages like Java, will not protect you in the least from doing these
types of mistakes. You may get an exception, if you are lucky! So if you
go down this path you are responsible for all your wrong doings...
"""

print """
example number 1 - removing elements in the list in the position before
the place where we are in. Result: Certain elements are never visited.
"""
size=10
l=range(size)
elements_visited=set()
all_elements=set(l)
for i,x in enumerate(l):
	if i==size//2: # integral division
		l.pop(0)
		l.pop(0)
		l.pop(0)
	elements_visited.add(x)
if len(elements_visited)!=size:
	print "elements_visited is %d while size is %d" % (len(elements_visited),size)
	print "This can cause problems for various algorithms"
print 'elements not visited are',all_elements-elements_visited
print 'remember that the element removed was 0...'

print """
example number 2 - removing elements in the list in the position before
the place where we are in but doing it on the last element
"""
size=10
l=range(size)
elements_visited=set()
all_elements=set(l)
for i,x in enumerate(l):
	if i==size-1:
		l.pop(0)
	try:
		elements_visited.add(l[i])
	except IndexError as e:
		print 'yes, got errors when accessing l[i]'
if len(elements_visited)!=size:
	print "elements_visited is %d while size is %d" % (len(elements_visited),size)
	print "This can cause problems for various algorithms"

print """
example number 3 - adding elements before the position that we are in
"""
size=10
l=range(size)
elements_visited=set()
all_elements=set(l)
for i,x in enumerate(l):
	if i==size//2: # integral division
		for y in xrange(3):
			l.insert(0,10+y)
	if x in elements_visited:
		print 'yep - we are visiting %d twice...' % (x)
	elements_visited.add(x)
if len(elements_visited)!=len(l):
	print "elements_visited is %d while size is %d" % (len(elements_visited),len(l))
	print "This can cause problems for various algorithms"
