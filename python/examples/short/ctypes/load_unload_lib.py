#!/usr/bin/python

"""
This is a piece of python code that loads a library and
releases it

You can see that this actually works by using pmap(1) on
the python process and seeing that the library libacl
is no longer there...

	Mark Veltzer <mark@veltzer.net>
"""

import ctypes
import dl
import signal

l=ctypes.cdll.LoadLibrary("libacl.so")
libdl=ctypes.CDLL("libdl.so")
libdl.dlclose(l._handle)
while True:
	signal.pause()
