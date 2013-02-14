#!/usr/bin/python

import subprocess

packs=[
	'scala', # for scala(1), scalac(1), scalap(1), scaladoc(1) and more...
]

args=['sudo','apt-get','install']
args.extend(packs)
subprocess.check_call(args)
