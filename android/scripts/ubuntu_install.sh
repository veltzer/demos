#!/bin/sh

# this scrip will install all the required packages that you need on
# ubuntu to compile and work with this package.

sudo apt-get install gcc-4.5-arm-linux-gnueabi gcc-4.4-arm-linux-gnueabi gperf git-core gnupg flex bison gperf build-essential zip curl zlib1g-dev libc6-dev libncurses5-dev x11proto-core-dev libx11-dev libreadline5-dev libz-dev libgl1-mesa-dev g++-multilib mingw32 tofrodos
# these I can't find in ubuntu using apt-cache search. Fortunately, the build works:
# ia32-libs