#!/usr/bin/tclsh

# An example of open
#
#	Mark Veltzer

set io [open "|ls" r]
puts "io is $io"
