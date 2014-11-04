#!/bin/sed -f

# this script does a replace (notice the -i above)
# use it like this:
# cp /etc/passwd /tmp/passwd
# replace_in_place /tmp/passwd

s/bash/dash/
