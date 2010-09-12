#!/usr/bin/python

# this next section is needed since we didn't "properly" install the module
# to /usr/share/python. If we had - this would not have been neccessary.

import sys
sys.path.append('build/lib.linux-i686-2.6');

import atoi

print atoi.atoi("-7.6");
