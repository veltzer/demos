#!/usr/bin/ruby

# An example of the standard funtional programming construct "map"
# in ruby...

superheroes = ["catwoman", "batman", "wonderwoman"]
print superheroes.map { |s| s.upcase } , "\n"
