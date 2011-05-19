#!/usr/bin/groovy

/*
	Some basic 'while' loop examples
*/

// most basic
def x=0
while(x<5) {
	x++
	println 'first loop '+x
}

// with break
x=0
while(x<5) {
	x++
	println 'second loop '+x
	if(x==3) {
		break
	}
}

// with continue
x=0
while(x<5) {
	x++
	if(x==3) {
		continue
	}
	println 'third loop '+x
}

/*
	No do/while syntax in groovy
// do/while
x=0
do {
	x++
	println 'fourth loop '+x
} while(x<5)
*/
