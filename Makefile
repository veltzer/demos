#SRC_CC:=$(shell find . -name "*.cc")
SRC_CC:=$(shell find user_space -name "*.cc")
EXE_CC:=$(addsuffix .exe,$(basename $(SRC_CC)))

EXE:=$(EXE_CC)

.PHONY: all
all: $(EXE)

.PHONY: clean
clean:
	-rm -f $(EXE)

#CODEGEN=-g3
CODEGEN=-O2 -s
FLAGS=-Wall -Werror $(CODEGEN) -Iinclude
CXXFLAGS:=$(FLAGS)

# general rules...
$(EXE_CC): %.exe: %.cc
	EXTRA_LIBS=`grep EXTRA_LIBS $< | cut -f 2 -d =`; EXTRA_CMDS=`grep EXTRA_CMDS $< | cut -f 2 -d =`; EXTRA_CMDS=$$($$EXTRA_CMDS); $(CXX) $(CXXFLAGS) -o $@ $< $$EXTRA_LIBS $$EXTRA_CMDS

.PHONY: debug
debug:
	$(info EXE_CC is $(EXE_CC))
