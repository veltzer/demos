#!/usr/bin/ruby

class A
	attr_accessor :ab, :ac
	def initialize(my_num)
		@my_num = my_num
	end
end

puts "describing the class..."
puts A.singleton_methods
puts A.methods
a = A.new(17)
puts "after constructor..."
puts a.instance_variables
a.ab = 'foo'
puts "after assignment..."
puts a.instance_variables

puts "reflection..."
a.instance_variables.each do|x|
	puts "Hello #{x}"
	puts x
	puts a.instance_variable_get x
end
