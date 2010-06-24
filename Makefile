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

DEBUG:=1
OPT:=0
ifeq ($(DEBUG),1)
CXXFLAGS:=$(CXXFLAGS) -g3
else
LDFLAGS:=$(LDFLAGS) -s
endif
ifeq ($(OPT),1)
CXXFLAGS:=$(CXXFLAGS) -O2
endif

ALL:=
CLEAN:=
CLEAN_DIRS:=

CC_SRC:=$(shell find user_space -name "*.cc") $(shell find kernel -name "*.cc") $(shell find ace -name "*.cc")
CC_ASX:=$(addsuffix .s,$(basename $(CC_SRC)))
CC_DIS:=$(addsuffix .dis,$(basename $(CC_SRC)))
CC_EXE:=$(addsuffix .exe,$(basename $(CC_SRC)))
ALL:=$(ALL) $(CC_EXE)
#ALL:=$(ALL) $(CC_DIS) $(CC_ASX)
CLEAN:=$(CLEAN) $(CC_EXE) $(CC_DIS) $(CC_ASX)

MOD_SRC=$(shell find kernel -name "drv_*.c" -and -not -name "drv_*.mod.c")
MOD_BAS=$(basename $(MOD_SRC))
MOD_OBJ=$(addsuffix .o,$(MOD_BAS))
MOD_SR2=$(addsuffix .mod.c,$(MOD_BAS))
MOD_OB2=$(addsuffix .mod.o,$(MOD_BAS))
MOD_CM1=$(addprefix kernel/.,$(addsuffix .ko.cmd,$(notdir $(MOD_BAS))))
MOD_CM2=$(addprefix kernel/.,$(addsuffix .mod.o.cmd,$(notdir $(MOD_BAS))))
MOD_CM3=$(addprefix kernel/.,$(addsuffix .o.cmd,$(notdir $(MOD_BAS))))
MOD_MOD:=$(addsuffix .ko,$(MOD_BAS))
ALL:=$(ALL) $(MOD_MOD)
CLEAN:=$(CLEAN) $(MOD_MOD) $(MOD_SR2) $(MOD_OB2) kernel/Module.symvers kernel/modules.order $(MOD_CM1) $(MOD_CM2) $(MOD_CM3) $(MOD_OBJ)
CLEAN_DIRS:=$(CLEAN_DIRS) kernel/.tmp_versions

.PHONY: all
all: $(ALL)

.PHONY: clean
clean:
	-rm -f $(CLEAN)
	-rm -rf $(CLEAN_DIRS)

#CODEGEN=-g3
#CODEGEN=-O2 -s
# optimization with debug info (for disassembly)
CODEGEN=-O2 -g3 -mtune=native
FLAGS=-Wall -Werror $(CODEGEN) -Iinclude
CXXFLAGS:=$(FLAGS)

# kernel module generation variables...

# kernel directory to build against
KDIR:=/lib/modules/$(shell uname -r)/build
# fill in the vervosity level you want for the kernel module compilation process
# V:=1 will give you the command lines used...
V:=0
# extra flags to pass to the kernel module creation process...
# regular kernels do not have -Werror and we want it!
KCFLAGS:=-Werror

# general rules...
# how to create regular executables...
$(CC_EXE): %.exe: %.cc
	EXTRA_LIBS=`grep EXTRA_LIBS $< | cut -f 2 -d =`; EXTRA_CMDS=`grep EXTRA_CMDS $< | cut -f 2 -d =`; EXTRA_CMDS=$$($$EXTRA_CMDS); $(CXX) $(CXXFLAGS) -o $@ $< $$EXTRA_LIBS $$EXTRA_CMDS
$(CC_ASX): %.s: %.cc
	EXTRA_LIBS=`grep EXTRA_LIBS $< | cut -f 2 -d =`; EXTRA_CMDS=`grep EXTRA_CMDS $< | cut -f 2 -d =`; EXTRA_CMDS=$$($$EXTRA_CMDS); $(CXX) $(CXXFLAGS) -S -o $@ $< $$EXTRA_LIBS $$EXTRA_CMDS
$(CC_DIS): %.dis: %.exe
	objdump --source --disassemble $< > $@
# rule about how to create .ko files...
$(MOD_MOD): %.ko: %.c
	$(MAKE) -C $(KDIR) V=$(V) KCFLAGS=$(KCFLAGS) M=$(abspath $(dir $<)) modules obj-m=$(addsuffix .o,$(notdir $(basename $<)))

.PHONY: debug
debug:
	$(info CC_SRC is $(CC_SRC))
	$(info CC_DIS is $(CC_DIS))
	$(info CC_EXE is $(CC_EXE))
	$(info MOD_SRC is $(MOD_SRC))
	$(info MOD_MOD is $(MOD_MOD))
	$(info ALL is $(ALL))
	$(info KDIR is $(KDIR))
	$(info V is $(V))
	$(info KCFLAGS is $(KCFLAGS))
	$(info CLEAN is $(CLEAN))
	$(info CLEAN_DIRS is $(CLEAN_DIRS))

# various checks...
.PHONY: check_ws
check_ws:
	-@grep "  " `find . -name "*.cpp" -or -name "*.h"`
.PHONY: check_files
check_files:
	-@find . -mindepth 2 -type f -and -not -name "*.cpp" -and -not -name "*.h" -and -not -name "*.h" -and -not -name "*.txt" -and -not -name "*.conf" -and -not -name "*.ini" -and -not -name "*.sample" -and -not -name "*.data" -and -not -name "*.doc" -and -not -name "*.bash"
.PHONY: check_include
check_include:
	-grep "include \"ace" `find . -name "*.cc" -or -name "*.h"`
.PHONY: check_tests_for_drivers
check_tests_for_drivers:
	cd kernel;for x in test_*.cc; do y=`echo $$x | cut -f 2- -d _`;z=drv_`basename $$y .cc`.c; if [ ! -f $$z ]; then echo "missing $$z"; fi ; done
	cd kernel;for x in drv_*.c; do y=`echo $$x | cut -f 2- -d _`;z=test_`basename $$y .c`.cc; if [ ! -f $$z ]; then echo "missing $$z"; fi ; done
.PHONY: check_extra_files
check_extra_files:
	find -type f -and -not -name "*.cc" -and -not -name "*.h" -and -not -name "*.c" -and -not -name "Makefile" -and -not -name "*.txt" -and -not -path "./.git/*" -and -not -name "*.sed" -and -not -name "*.patch" -and -not -name "*.mk" -and -not -name "*.cfg"

# kernel section
.PHONY: kern_help
kern_help:
	$(MAKE) -C $(KDIR) help
.PHONY: kern_tail
kern_tail:
	sudo tail /var/log/kern.log
.PHONY: kern_tailf
kern_tailf:
	sudo tail -f /var/log/kern.log
.PHONY: kern_syslog_tail
kern_syslog_tail:
	sudo tail /var/log/kern.log
.PHONY: kern_syslog_tailf
kern_syslog_tailf:
	sudo tail -f /var/log/kern.log
.PHONY: kern_dmesg
kern_dmesg:
	@sudo dmesg
.PHONY: kern_dmesg_clean
kern_dmesg_clean:
	@sudo dmesg -c > /dev/null
.PHONY: kern_halt
kern_halt:
	sudo halt
.PHONY: kern_reboot
kern_reboot:
	sudo reboot
.PHONY: kern_tips
kern_tips:
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

# code beautifucation
.PHONY: do_beautify
do_beautify:
	uncrustify -c uncrustify.cfg --replace --no-backup --mtime $(CC_SRC)
