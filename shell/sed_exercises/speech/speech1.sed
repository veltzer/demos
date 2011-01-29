#!/bin/sed -nf

# execute this code anyway
H

/\./ {
	g
	s/\n/ /g
	s/\. */\.\n/g
	s/^ *//
	h
	s/\..*$/./g
	p
	g
	s/^.*\.//g
	h
}
$ {
	g
	s/^\n//g
	p
}
