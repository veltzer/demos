#!/usr/bin/groovy

/*
	Triple quoted strings
*/

very_long_text="""This is some
very
very
very
long text"""
println very_long_text

// lets try to avoid newlines...
very_long_text="""This is some\
very\
very\
very\
long text"""
println very_long_text
