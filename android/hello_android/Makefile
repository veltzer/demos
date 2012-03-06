TOOLCHAIN:=/home/mark/install/android_platform/android-ndk-r7/toolchains/arm-linux-androideabi-4.4.3/prebuilt/linux-x86/bin
GCC:=$(TOOLCHAIN)/arm-linux-androideabi-gcc
FLAGS:=-I/home/mark/install/android_platform/android-ndk-r7/platforms/android-14/arch-arm/usr/include/

# YOUR TOOLCHAIN IN
# ~/WORKING_DIRECTORY/mydroid/prebuild/linux-x86/toolchain/arm-linux-androideabi-4.2.1/bin/arm-****-gcc

hello: hello.c
	$(GCC) $(FLAGS) -o hello hello.c

.PHONY: clean
clean:
	-rm -f hello
