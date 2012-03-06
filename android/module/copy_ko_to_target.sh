#!/bin/sh
export TOOLS=~/install/android_platform/android-sdk-linux-r16/platform-tools/
export PATH=$TOOLS:$PATH
~/install/android_platform/android-sdk-linux-r16/platform-tools/adb push hello.ko /data
