#!/usr/bin/python3

# This script creates a valid java classpath from a list of jars

import sys # for argv

print(':'.join(sys.argv[1:]))
