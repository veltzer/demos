#!/usr/bin/groovy

/*
	An example showing basic 'for' loop in groovy

	Notes:
	- strings are just containers for their characters.
	- lists are iterable using a 'for' loop.
*/

// A String is nothing but a sequence of characters.
def hello = "Hello"
for(aChar in hello){
	println aChar
}

// A Collection object holding the four seasons.
def seasons = ["Winter", "Spring", "Summer", "Autumn"]
for(season in seasons){
	println season
}

// A Collection object holding the four seasons.
for(String season in seasons){
	println season
}

// show that the 'for' loop variable is not available after the loop is over
try {
	println season
} catch(MissingPropertyException e) {
	println 'yes, you cannot access a variable after the loop is over'
}

/*
File line iteration using a for loop is deprecated
// Even a File Object can be iterated in Groovy.
String thisFileName = "Loop.groovy"
thisFile = new File(thisFileName)
for(aLine in thisFile){
	println aLine
}
*/

// break in the middle of a loop 
for(String season in seasons){
	if(season=='Spring') {
		break
	}
	println season
}

d=[
	'Paris':'France',
	'Jerusalem':'Israel',
]
for(x in d){
	println "${x.key} is the capital of ${x.value}"
}
