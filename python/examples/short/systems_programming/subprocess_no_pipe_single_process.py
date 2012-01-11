#!/usr/bin/python

"""
A simple subprocess demo. Create a subprocess and run it. No pipes.
Find the return code of the process.

	Mark Veltzer <mark@veltzer.net>
"""

import subprocess
p=subprocess.Popen(['sleep','10'])
print('in here, async, isnt it?');
ret=p.wait()
print('ret is',ret)
