#!/usr/bin/python

# this script will install all the required packages that you need on
# ubuntu to compile and work with this package.

import subprocess

packs=[
	'pyqt4-dev-tools',
	'python-qt4',
	'python-qt4-sql',
	'python-qt4-dbus',
	'python3-pyqt4',
	'python3-dbus.mainloop.qt',
]

args=['sudo','apt-get','install']
args.extend(packs)
subprocess.check_call(args)
