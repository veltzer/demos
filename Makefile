ALL:=

CC_SRC:=$(shell find user_space -name "*.cc") $(shell find kernel -name "*.cc")
CC_EXE:=$(addsuffix .exe,$(basename $(CC_SRC)))
ALL:=$(ALL) $(CC_EXE)

MOD_SRC=$(shell find kernel -name "drv_*.c" -and -not -name "drv_*.mod.c")
MOD_MOD:=$(addsuffix .ko,$(basename $(MOD_SRC)))
ALL:=$(ALL) $(MOD_MOD)

.PHONY: all
all: $(ALL)

.PHONY: clean
clean:
	-rm -f $(ALL)

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
