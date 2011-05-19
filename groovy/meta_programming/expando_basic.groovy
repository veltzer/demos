#!/usr/bin/groovy

/*
	This shows the type of the metaClass attribute
*/

ExpandoMetaClass.enableGlobally()

String s='hello'

println "s is ${s}"
println "s.class is ${s.class}"
println "s.metaClass is ${s.metaClass}"
println "s.metaClass.class is ${s.metaClass.class}"
