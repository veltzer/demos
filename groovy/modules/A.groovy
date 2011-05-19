/*
	This is a groovy module supplying some functions.

	Notes:
	- this file itself need not be executable.
	- this file itself need not be with a first 'shbang' line.
*/

// this method could be used using a.sum
static def myStaticMethod(a,b) {
	return a+b
}
// this method cannot be used...
def myNonStaticMethod(a,b) {
	return a+b
}
private static def myStaticPrivateMethod(a,b) {
	return a+b
}
