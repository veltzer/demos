#!/usr/bin/python

import os

os.system("ls -l")
print "Im still here"
os.execl("/bin/ls");
print "Where am I?"
