#!/usr/bin/python

# this script will install all the required packages that you need on
# ubuntu to compile and work with this package.

import subprocess

packs=[
	'cowsay',
	'figlet',
	'sl',
]

args=['sudo','apt-get','install']
args.extend(packs)
subprocess.check_call(args)
