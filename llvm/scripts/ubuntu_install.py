#!/usr/bin/python3

# this script will install all the required packages that you need on
# ubuntu to compile and work with this package.

# TODO:

import subprocess # for check_call()

packs=[
	'llvm-gcc-4.8',
	'llvm-runtime',
]

args=['sudo','apt-get','install']
args.extend(packs)
subprocess.check_call(args)
