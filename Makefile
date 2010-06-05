ALL:=

CC_SRC:=$(shell find user_space -name "*.cc") $(shell find kernel -name "*.cc")
CC_EXE:=$(addsuffix .exe,$(basename $(CC_SRC)))
ALL:=$(ALL) $(CC_EXE)

MOD_SRC=$(shell find . -name "drv_*.c" -and -not -name "drv_*.mod.c")
MOD_MOD:=$(addsuffix .ko,$(basename $(MOD_SRC)))
#ALL:=$(ALL) $(MOD_MOD)

.PHONY: all
all: $(ALL)

.PHONY: clean
clean:
	-rm -f $(ALL)

#CODEGEN=-g3
CODEGEN=-O2 -s
FLAGS=-Wall -Werror $(CODEGEN) -Iinclude
CXXFLAGS:=$(FLAGS)

# general rules...
$(CC_EXE): %.exe: %.cc
	EXTRA_LIBS=`grep EXTRA_LIBS $< | cut -f 2 -d =`; EXTRA_CMDS=`grep EXTRA_CMDS $< | cut -f 2 -d =`; EXTRA_CMDS=$$($$EXTRA_CMDS); $(CXX) $(CXXFLAGS) -o $@ $< $$EXTRA_LIBS $$EXTRA_CMDS

.PHONY: debug
debug:
	$(info CC_SRC is $(CC_SRC))
	$(info CC_EXE is $(CC_EXE))
	$(info MOD_SRC is $(MOD_SRC))
	$(info MOD_MOD is $(MOD_MOD))
	$(info ALL is $(ALL))
