# we handle both C and C++ executables in this file...
SRC_CC:=$(shell find . -name "*.cc")
SRC_CC:=$(shell find user_space -name "*.cc")
EXE_CC:=$(addsuffix .exe,$(basename $(SRC_CC)))
CC:=gcc

EXE:=$(EXE_CC)

.PHONY: all
all: $(EXE)

.PHONY: clean
clean:
	-rm -f $(EXE)

#FLAGS=-Wall -Werror -O2 -s
#FLAGS:=-Wall -Werror -O2
FLAGS=-Wall -Werror -g3 -Iinclude
CXXFLAGS:=$(FLAGS)
CXXFLAGS_NOOPT:=-Wall -Werror

# general rules...
$(EXE_CC): %.exe: %.cc
	EXTRA_LIBS=`grep EXTRA_LIBS $< | cut -f 2 -d =`; EXTRA_CMDS=`grep EXTRA_CMDS $< | cut -f 2 -d =`; EXTRA_CMDS=$$($$EXTRA_CMDS); $(CXX) $(CXXFLAGS) -o $@ $< $$EXTRA_LIBS $$EXTRA_CMDS

.PHONY: debug
debug:
	$(info EXE_CC is $(EXE_CC))
