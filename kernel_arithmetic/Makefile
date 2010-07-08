# the name of the module we are doing
NAME:=demo
# object files to compile for the kernel module
obj-m:=demo.o
# extra objects in the "top" part of the driver
demo-objs:=drv_div.o div64.o
# fill in the name of the genrated ko file
MOD:=demo.ko

# fill in the directory of the kernel build system
KDIR:=/lib/modules/$(shell uname -r)/build
#KDIR=/home/mark/rafael/nu/kernel_headers/2.6.28-16-generic/build
# fill in the vervosity level you want
V:=0
# flags for the C++ compiler
CXXFLAGS:=-Wall -Werror -O2 -lpthread -I../include
# regular kernels do not have -Werror and we want it!
KCFLAGS:=-Werror

FLAGS:=-C $(KDIR) $(CROSS_FLAGS) M=$(CURDIR) V=$(V) KCFLAGS=$(KCFLAGS)

TESTS:=$(shell find . -name "test_*.cc")
EXES:=$(basename $(TESTS))

all: $(EXES) modules

modules:
	$(MAKE) $(FLAGS) modules
modules_install:
	$(MAKE) $(FLAGS) modules_install
clean_kernel:
	$(MAKE) $(FLAGS) clean
	# some how this file does get cleaned
	-@rm -f modules.order Module.markers
clean: clean_kernel
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

debug:
	@echo KDIR is $(KDIR)
	@echo MOD is $(MOD)
	@echo V is $(V)
	@echo obj-m is $(obj-m)
	@echo demo-objs is $(demo-objs)
	@echo NAME is $(NAME)
	@echo TESTS is $(TESTS)
	@echo EXES is $(EXES)

SRC_TESTS:=$(shell find . -type f -name "test_*.cc")
SRC_DRVS:=$(shell find . -type f -name "drv_*.c")
check:
	for x in test_*.cc; do y=`echo $$x | cut -f 2- -d _`;z=drv_`basename $$y .cc`.c; if [ ! -f $$z ]; then echo "missing $$z"; fi ; done
	for x in drv_*.c; do y=`echo $$x | cut -f 2- -d _`;z=test_`basename $$y .c`.cc; if [ ! -f $$z ]; then echo "missing $$z"; fi ; done

test_complete: test_complete.cc
	$(CXX) $(CXXFLAGS) -lcpufreq -o $@ $<

KERN_LD:=ld
# this was taken from running standard module build on ubuntu with V=1
KERN_LD_FLAGS:=--build-id -r -m elf_i386
KERN_OBJS:=demo.mod.o drv_div.o
#LIBGCC:=/usr/lib/gcc/i486-linux-gnu/$(shell gcc -dumpversion)/libgcc.a
LIBGCC:=/usr/lib/gcc/i486-linux-gnu/4.1/libgcc.a
relink:
	$(KERN_LD) $(KERN_LD_FLAGS) -o demo.ko $(KERN_OBJS) $(LIBGCC)
