#!/usr/bin/groovy

/*
	This is an example of a closure with variable arguments
*/

def makeList= { Object[] args ->
	def l=[]
	args.collect(l,{ it })
	return l
}

println 'with 3 arguments: '+makeList(7,8,9)
println 'with no arguments: '+makeList()
println 'with 1 argument: '+makeList('fubar')
