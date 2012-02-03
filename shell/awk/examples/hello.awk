#!/usr/bin/awk -f
{ print "in middle" }
BEGIN { print "in begining2" }
END { print "in end" }
{ print "in middle2" }
BEGIN { print "in begining" }
