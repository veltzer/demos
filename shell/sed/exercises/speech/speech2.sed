#!/bin/sed -nf
# Add next line to buffer
:nextline
N
# Convert it to one long string substituting \n to space
s/\n/ /
:printline
# Split string at .
s/\./\.\n/
# If we don't have .
/\./! {
# If it was last string - print all
  $b end
# Else get next line
  b nextline
}
# Delete spaces at the begining 
s/^ *//
# Print first part of string
P
# Delete printed part
s/^.*\n//
/\./b printline
# If it was last string - print it
$b end
# Else get next line
b nextline
:end
# Delete spaces at the begining 
s/^ //
# Print all
p
