#!/bin/csh

# this example shows that the csh return code is the return code of the last command
# use echo $? after running this script to see it's return code
# This example also shows that lines which fails do not produce any error nor do they
# influence the return code of the entire script...

true
false
true
