#!/usr/bin/python

# this example shows that python is strict about indentation and
# will fail the compilation/running if the code is not strictly
# indented.

x=[1,2,3]
for y in x:
 if y==2:
 	print y
  print y
