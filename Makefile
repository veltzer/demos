ALL:=
CLEAN:=
CLEAN_DIRS:=

CC_SRC:=$(shell find user_space -name "*.cc") $(shell find kernel -name "*.cc")
CC_EXE:=$(addsuffix .exe,$(basename $(CC_SRC)))
ALL:=$(ALL) $(CC_EXE)
CLEAN:=$(CLEAN) $(CC_EXE)

MOD_SRC=$(shell find kernel -name "drv_*.c" -and -not -name "drv_*.mod.c")
MOD_BAS=$(basename $(MOD_SRC))
MOD_SR2=$(addsuffix .mod.c,$(MOD_BAS))
MOD_OB2=$(addsuffix .mod.o,$(MOD_BAS))
MOD_CM1=$(addprefix kernel/.,$(addsuffix .ko.cmd,$(notdir $(MOD_BAS))))
MOD_CM2=$(addprefix kernel/.,$(addsuffix .mod.o.cmd,$(notdir $(MOD_BAS))))
MOD_CM3=$(addprefix kernel/.,$(addsuffix .o.cmd,$(notdir $(MOD_BAS))))
MOD_MOD:=$(addsuffix .ko,$(MOD_BAS))
ALL:=$(ALL) $(MOD_MOD)
CLEAN:=$(ALL) $(MOD_MOD) $(MOD_SR2) $(MOD_OB2) kernel/Module.symvers kernel/modules.order $(MOD_CM1) $(MOD_CM2) $(MOD_CM3)
CLEAN_DIRS:=$(CLEAN_DIRS) kernel/.tmp_versions

.PHONY: all
all: $(ALL)

.PHONY: clean
clean:
	-rm -f $(CLEAN)
	-rm -rf $(CLEAN_DIRS)

#CODEGEN=-g3
CODEGEN=-O2 -s
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
# rule about how to create .ko files...
$(MOD_MOD): %.ko: %.c
	$(MAKE) -C $(KDIR) V=$(V) KCFLAGS=$(KCFLAGS) M=$(abspath $(dir $<)) modules obj-m=$(addsuffix .o,$(notdir $(basename $<)))

.PHONY: debug
debug:
	$(info CC_SRC is $(CC_SRC))
	$(info CC_EXE is $(CC_EXE))
	$(info MOD_SRC is $(MOD_SRC))
	$(info MOD_MOD is $(MOD_MOD))
	$(info ALL is $(ALL))
	$(info KDIR is $(KDIR))
	$(info V is $(V))
	$(info KCFLAGS is $(KCFLAGS))
	$(info CLEAN is $(CLEAN))
	$(info CLEAN_DIRS is $(CLEAN_DIRS))
