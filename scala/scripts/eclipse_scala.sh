#!/bin/bash

# before running this script make sure that you have eclipse for java installed
# in ~/install/eclipse-java (may be soft link).
~/install/eclipse-scala/eclipse -data ~/workspace-scala -vmargs -Xmx1524M 1> /dev/null 2> /dev/null &
