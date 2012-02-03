#!/bin/sed -nf

# simply hold everything in the holding area
H

$ {
	# grab from holding
	g
	# all newlines must go
	s/\n/ /g
	# turn dots with spaces after them to dots with newlines
	s/\. */\.\n/g
	# get ridd of space at first line (Damn that H)
	s/^ *//
	# print the result
	p
}
