#!/bin/sed -f

# this sed script changes question to answer

/^question/ {
	h
	d
}
$g
