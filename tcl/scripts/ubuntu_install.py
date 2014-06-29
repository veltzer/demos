#!/usr/bin/python3

# this script will install all the required packages that you need on
# ubuntu to compile and work with this package.

# TODO:

import subprocess # for check_call()

version='8.6'

packs=[
	'tk',
	'tcl',
	'tcl-doc',
	'tcl{0}'.format(version),
	'tcl{0}-dev'.format(version),
	'tcl{0}-doc'.format(version),
]

args=['sudo','apt-get','install']
args.extend(packs)
subprocess.check_call(args)
