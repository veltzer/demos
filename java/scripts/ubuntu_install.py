#!/usr/bin/python

# this script will install all the required packages that you need on
# ubuntu to compile and work with this package.

# the application -> packages that I need are:
# jvisualvm -> visualvm
# jmap -> openjdk-6-jdk
# jhat -> openjdk-6-jdk
# jconsole -> openjdk-6-jdk

import subprocess

packs=[
	'visualvm',
	#'openjdk-6-jre',
	#'openjdk-6-jdk',
	#'openjdk-6-doc',
	'openjdk-7-jre',
	'openjdk-7-jdk',
	'openjdk-7-doc',
	'ivy',
	'ivy-doc',
	'ant',
	'ant-doc',
	'ant-optional',
	'ant-contrib',
]

args=['sudo','apt-get','install']
args.extend(packs)
subprocess.check_call(args)
