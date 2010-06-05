MODULE_SOURCES=$(shell find . -name "drv_*.c" -and -not -name "drv_*.mod.c")
MODULE_KO:=$(addsuffix .ko,$(basename $(MODULE_SOURCES)))

DEBUG=1
OPT=0
TARGET:=curr
ifeq ($(TARGET),curr)
KDIR:=/lib/modules/$(shell uname -r)/build
CXX:=g++
endif
ifeq ($(TARGET),target_release)
KDIR:=/home/mark/rafael/sources/rafael-rel/filesystem/root/lib/modules/2.6.26.5-rt9/build
TOOLCHAIN_BASE:=/opt/Rafael/i686-unknown-linux-gnu/gcc-4.1.2-glibc-2.7-kernel-2.6.18/bin/i686-unknown-linux-gnu-
CXX:=$(TOOLCHAIN_BASE)g++
endif
# flags for the C++ compiler
CXXFLAGS:=-Wall -Werror -I../include
# flags for the linker
LDFLAGS=

ifeq ($(DEBUG),1)
CXXFLAGS+=-g3
else
LDFLAGS+=-s
endif
ifeq ($(OPT),1)
CXXFLAGS+=-O2
endif
# kernel directory to build against
KDIR:=/lib/modules/$(shell uname -r)/build
# fill in the vervosity level you want for the kernel module compilation process
# V:=1 will give you the command lines used...
V:=0
# extra flags to pass to the kernel module creation process...
# regular kernels do not have -Werror and we want it!
KCFLAGS:=-Werror
# here be all the flags passed to the kernel compilation process
FLAGS:=-C $(KDIR) V=$(V) KCFLAGS=$(KCFLAGS) M=$(CURDIR)

TESTS:=$(shell find . -name "test_*.cc")
EXES:=$(addsuffix .exe,$(basename $(TESTS)))

all: $(EXES) $(MODULE_KO)

$(EXES): %.exe: %.cc
	$(CXX) $(LDFLAGS) $(CXXFLAGS) -o $@ $< -lpthread -lcpufreq
modules:
	$(MAKE) $(FLAGS) modules
modules_install:
	$(MAKE) $(FLAGS) modules_install
.PHONY: clean_kernel
clean_kernel:
	$(MAKE) $(FLAGS) clean
	# some how this file does get cleaned
	-@rm -f modules.order Module.markers
.PHONY: clean
clean:
	# remove the kernel stuff
	-rm -f $(MODULE_KO) $(MODULE_OBJ) modules.order Module.markers Module.symvers *.mod.o *.mod.c .*.o.cmd .*.ko.cmd
	-rm -rf .tmp_versions
	# remove the user space
	-@rm -f $(EXES) 
help:
	$(MAKE) $(FLAGS) help
insmod:
	sudo insmod $(MOD) 
lsmod:
	sudo lsmod | grep $(NAME)
rmmod:
	sudo rmmod $(NAME)
modinfo:
	sudo modinfo $(MOD)
kern.tail:
	sudo tail /var/log/kern.log
kern.tailf:
	sudo tail -f /var/log/kern.log
syslog.tail:
	sudo tail /var/log/kern.log
syslog.tailf:
	sudo tail -f /var/log/kern.log
dmesg:
	@sudo dmesg
dmesg_clean:
	@sudo dmesg -c > /dev/null
halt:
	sudo halt
reboot:
	sudo reboot
tips:
	@echo "do make V=1 [target] to see more of what is going on"
	@echo
	@echo "in order for the operational targets to work you need to"
	@echo "make sure that can do 'sudo', preferably with no password."
	@echo "one way to do that is to add yourself to the 'sudo' group"
	@echo "and add to the /etc/sudoers file, using visudo, the line:"
	@echo "%sudo ALL=NOPASSWD: ALL"
	@echo
	@echo "you can compile your module to a different kernel version"
	@echo "like this: make KVER=2.6.13 [target]"
	@echo "or edit the file and permanently change the version"

debug:
	@echo MODULE_SOURCES is $(MODULE_SOURCES)
	@echo MODULE_KO is $(MODULE_KO)
	@echo KDIR is $(KDIR)
	@echo V is $(V)
	@echo NAME is $(NAME)
	@echo TESTS is $(TESTS)
	@echo EXES is $(EXES)

SRC_TESTS:=$(shell find . -type f -name "test_*.cc")
SRC_DRVS:=$(shell find . -type f -name "drv_*.c")
check:
	for x in test_*.cc; do y=`echo $$x | cut -f 2- -d _`;z=drv_`basename $$y .cc`.c; if [ ! -f $$z ]; then echo "missing $$z"; fi ; done
	for x in drv_*.c; do y=`echo $$x | cut -f 2- -d _`;z=test_`basename $$y .c`.cc; if [ ! -f $$z ]; then echo "missing $$z"; fi ; done

# rule about how to create .ko files...
$(MODULE_KO): %.ko: %.c
	$(MAKE) $(FLAGS) modules obj-m=$(addsuffix .o,$(basename $<))
