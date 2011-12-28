#!/usr/bin/python2.7
# leave this 2.7 thing alone!

"""
This is a script that runs in a python specific version.
You can also see that via the print that it does.

In ubuntu you can install other versions of python this way:
sudo apt-get install python2.7
sudo apt-get install python3
/usr/bin/python in most distributions is just a symlink. It is
usually a bad idea to change that symlink since this is the
"default" python for the distribution. Remmember that python is
used for a lot more in a modern distribution than just run your
code and so the default version should be determined by the
distribution vendor and not by you. If you want to use a different
version of python install it (either from the vendor if it's available
or from binary or source code on your own) and use your own version
explicitly with the shbang line or envrionment variables, or maybe
absolute path on the command line.

	Mark Veltzer <mark@veltzer.net>
"""
import sys
print 'python version is',sys.version