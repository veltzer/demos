CPPFLAGS=-O2 -g -Wall -Werror -I. -D_REENTRANT
LDFLAGS=-lACE
CXX=g++
SOURCES=$(shell find . -name "*.cpp")

OBJECTS=$(SOURCES:.cpp=.o)
PROGS=$(SOURCES:.cpp=)

.POHNY: all
all: $(PROGS)

.PHONY: clean
clean:
	-rm -f $(PROGS)

check_ws:
	-@grep "  " `find . -name "*.cpp" -or -name "*.h"`

check_files:
	-@find . -mindepth 2 -type f -and -not -name "*.cpp" -and -not -name "*.h" -and -not -name "*.h" -and -not -name "*.txt" -and -not -name "*.conf" -and -not -name "*.ini" -and -not -name "*.sample" -and -not -name "*.data" -and -not -name "*.doc" -and -not -name "*.bash"

check_include:
	-@grep "include \"ace" `find . -name "*.cpp" -or -name "*.h"`
