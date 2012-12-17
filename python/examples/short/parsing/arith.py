#!/usr/bin/python

# doing simple arithmetic evaluation in python

import pyparsing
import sys

number=pyparsing.Word(pyparsing.nums).setParseAction(lambda t:int(t[0]))
multop=pyparsing.oneOf('* /')
plusop=pyparsing.oneOf('+ -')
expr=pyparsing.operatorPrecedence( number,
[
	(plusop, 2, pyparsing.opAssoc.LEFT),
	(multop, 2, pyparsing.opAssoc.LEFT),
])

print expr.parseString(sys.argv[1])
