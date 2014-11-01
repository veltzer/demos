#!/bin/bash -u

<<COMMENT

This is an example of how to do string manipulations in bash

References:
http://tldp.org/LDP/abs/html/string-manipulation.html

COMMENT

stringZ=abcABC123ABCabc
#	0123456789.....
#	0-based indexing.

echo ${stringZ:0} # abcABC123ABCabc
echo ${stringZ:1} # bcABC123ABCabc
echo ${stringZ:7} # 23ABCabc

echo ${stringZ:7:3} # 23A - Three characters of substring.

# Is it possible to index from the right end of the string?

echo ${stringZ:-4} # abcABC123ABCabc - Defaults to full string, as in ${parameter:-default}.

# However...

echo ${stringZ:(-4)} # Cabc - Now, it works.
echo ${stringZ: -4} # Cabc - Parentheses or added space "escape" the position parameter.
