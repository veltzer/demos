#!/usr/bin/python3

'''
this script will install all the required packages that you need on
ubuntu to compile and work with this package.
'''

import subprocess # for check_call

packs=[
	# console fun
	'cowsay',
	'figlet',
	'sl',

	# xpath
	'libxml-xpath-perl',

	# llvm
	'llvm-3.4',
	'llvm-3.4-doc',
	'llvm-3.5',
	'llvm-3.5-doc',
	'llvm-3.6',
	'llvm-3.6-doc',
	'llvm-runtime',

	# scala
	'scala', # for scala(1), scalac(1), scalap(1), scaladoc(1) and more...

	# csharp
	'mono-runtime',
	'monodevelop',

	# android
	#'gcc-arm-linux-gnueabi',
	# these are old versions which are no longer supported on ubuntu
	#'gcc-4.5-arm-linux-gnueabi',
	#'gcc-4.4-arm-linux-gnueabi',
	'gperf',
	'git-core',
	'gnupg',
	'flex',
	'bison',
	'gperf',
	'build-essential',
	'zip',
	'curl',
	'zlib1g-dev',
	'libc6-dev',
	'libncurses5-dev',
	'x11proto-core-dev',
	'libx11-dev',
	#'libreadline-gplv2-dev',
	'zlib1g-dev',
	'libgl1-mesa-dev',
	'g++-multilib',
	'tofrodos',
	# these I can't find in ubuntu using apt-cache search. Fortunately, the build works:
	#'ia32-libs',
]

args=['sudo','apt-get','install','--assume-yes']
args.extend(packs)
subprocess.check_call(args)
