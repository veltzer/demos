# where is the cross compiler?
CROSS_COMPILE:=~/install/android_platform/android-ndk-r7/toolchains/arm-linux-androideabi-4.4.3/prebuilt/linux-x86/bin/arm-linux-androideabi-
CROSS_COMPILE:=~/install/android_platform/android_build/WORKING_DIRECTORY/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-
# what is the architecture we want to build?
ARCH:=x86
ARCH:=arm
# the base name of the module
NAME:=hello
# the ko file
KO:=$(NAME).ko
# fill in the name of the module. I know that it's weird but this should be a .o name
# and is probably due to backwards compatibility issues.
obj-m:=$(NAME).o
# This is not strictly required for a single object module where the name of the module
# is the name of the single file in it. In my case this is not the case since the module
# is hello.ko while the single file is main.c.
$(NAME)-objs:=main.o
# fill in the version of the kernel for which you want the module compiled to
# the ?= means that it will only be set if you have not provided this via the command line.
# You can override using "make KVER=2.6.35".
KVER?=$(shell uname -r)
# KDIR for regular ubuntu or linux disto kernels...
KDIR:=/lib/modules/$(KVER)/build
# KDIR for android...
KDIR:=~/install/android_platform/android-kernel/
# fill in the vervosity level you want
# You can override using "make V=1"
V?=0

.PHONY: modules
modules:
	$(MAKE) -C $(KDIR) M=$(CURDIR) ARCH=$(ARCH) CROSS_COMPILE=$(CROSS_COMPILE) V=$(V) modules
.PHONY: modules_install
modules_install:
	$(MAKE) -C $(KDIR) M=$(CURDIR) ARCH=$(ARCH) CROSS_COMPILE=$(CROSS_COMPILE) V=$(V) modules_install
.PHONY: clean
clean:
	$(MAKE) -C $(KDIR) M=$(CURDIR) ARCH=$(ARCH) CROSS_COMPILE=$(CROSS_COMPILE) V=$(V) clean
.PHONY: help
help:
	$(MAKE) -C $(KDIR) M=$(CURDIR) ARCH=$(ARCH) CROSS_COMPILE=$(CROSS_COMPILE) V=$(V) help
# Indents the kernel source the way linux/Documentation/CodingStyle.txt
# wants it to be.
.PHONY: indent
indent:
	indent -kr -i8 *.c
# a target to debug my own makefile...
.PHONY: debug
debug:
	$(info NAME is $(NAME))
	$(info KO is $(KO))
	$(info KVER is $(KVER))
	$(info KDIR is $(KDIR))
	$(info V is $(V))
	$(info CROSS_COMPILE is $(CROSS_COMPILE))
	$(info ARCH is $(ARCH))
	$(info CURDIR is $(CURDIR))
	$(info obj-m is $(obj-m))
	$(info $(NAME)-objs is $($(NAME)-objs))
# easy macros to enable kernel development
.PHONY: insmod
insmod:
	-sudo rmmod $(NAME)
	sudo insmod $(KO)
.PHONY: rmmod
rmmod:
	sudo rmmod $(NAME)
.PHONY: modinfo
modinfo:
	modinfo $(KO)
