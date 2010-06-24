CPPFLAGS=-s -O2 -Wall -Werror -I. -D_REENTRANT -lACE
CXX=g++
SOURCES=$(shell find . -name "*.cpp")
PROGS=$(SOURCES:.cpp=.exe)

.PHONY: all
all: $(PROGS)

# a rule to make programs
$(PROGS): %.exe: %.cpp
	$(CXX) $(CPPFLAGS) -o $@ $<

.PHONY: clean
clean:
	-rm -f $(PROGS)

# various checks...
.PHONY: check_ws
check_ws:
	-@grep "  " `find . -name "*.cpp" -or -name "*.h"`

.PHONY: check_files
check_files:
	-@find . -mindepth 2 -type f -and -not -name "*.cpp" -and -not -name "*.h" -and -not -name "*.h" -and -not -name "*.txt" -and -not -name "*.conf" -and -not -name "*.ini" -and -not -name "*.sample" -and -not -name "*.data" -and -not -name "*.doc" -and -not -name "*.bash"

.PHONY: check_include
check_include:
	-@grep "include \"ace" `find . -name "*.cpp" -or -name "*.h"`

# debug
.PHONY: debug
debug:
	$(info SOURCES is $(SOURCES))
	$(info PROGS is $(PROGS))
