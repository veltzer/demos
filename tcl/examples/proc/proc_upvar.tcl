#!/usr/bin/tclsh

proc one {} {
	set z 5
	puts "z is $z"
	two
	puts "z is $z"
}

proc two {} {
	upvar 1 z y
	set y 7
	#puts "y is $y"
}

one
