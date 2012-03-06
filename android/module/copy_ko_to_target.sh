#!/bin/sh
export TOOLS=~/install/android_platform/android-sdk-linux-r16/platform-tools/
export PATH=$TOOLS:$PATH
adb push hello.ko /data
