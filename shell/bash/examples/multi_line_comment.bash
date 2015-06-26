#!/bin/bash -u

<<COMMENT

This is an example of how to do multi line comments in bash.

References:
http://www.cyberciti.biz/faq/bash-comment-out-multiple-line-code/

COMMENT

<<COMMENT

This is a multi line comment

COMMENT

: '

This is also a multi line comment

'

echo "Hello, World!"
