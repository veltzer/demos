#!/usr/bin/tclsh

proc sumall {args} {
	set sum 0
	foreach x $args {
		incr sum $x
	}
	return $sum
}
set myresult [sumall 5 6 7 8 9]
puts "myresult is $myresult"
