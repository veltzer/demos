#!/usr/bin/ruby

# This example explores the different ways to create an array in Ruby...

# built in syntax
print [],"\n" # []
print ["a","b"],"\n" # ["a", "b"]
# constructor
print Array.new(),"\n" # []
print Array.new(0),"\n" # []
print Array.new(5),"\n" # [nil, nil, nil, nil, nil]
# constructor, funtional style
print Array.new(5) { |i| i*2 },"\n" # [0, 2, 4, 6, 8]
# with a size
print Array.new(5),"\n" # [nil, nil, nil, nil, nil]
# with a size and object
print Array.new(3,"a"),"\n" # ["a", "a", "a"]
# using the [] operator
print Array.[],"\n" # []
print Array.[]("x","y","z"),"\n" # ["x", "y", "z"]
# copy constructor
print Array.new(["one","two"]),"\n"
