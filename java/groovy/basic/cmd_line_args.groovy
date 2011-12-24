#!/usr/bin/groovy

/*
	This example shows how you can access command line arguments
	when writing a non OO script in groovy.
	As a reminder, when you write OO code in groovy your entry point
	is the static method 'main' (much line in regular Java). If you
	pick this way of writing your script then you get the arguments
	as the single argument passed to that method.
	If, on the other hand, you want to write more conventional 'scripts'
	then you have the automatic variable 'this' from which you can get
	info, including command line args.
	The 'this' variable is an instance of the script you are running.
	In this case it is an instance of 'cmd_line_args'.
*/

println "Hello, World!i "+this
for (a in this.args) {
	println("Argument: " + a)
}
