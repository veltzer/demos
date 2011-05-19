#!/usr/bin/groovy

/*
	Variable interpolation in strings

	As you can see it only works in double or triple quoted strings...
	That is why experienced Groovy developers use single quoted strings when
	they do not do interpolation (to save runtime...).
*/

double pi=3.14
s1='pi is ${pi}'
s2="pi is ${pi}"
s3="""pi is ${pi}"""
println s1
println s2
println s3
