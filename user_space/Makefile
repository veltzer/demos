# we handle both C and C++ executables in this file...
SRC_CC:=$(shell find . -name "*.cc")
SRC_C:=$(shell find . -name "*.c")
EXE_CC:=$(basename $(SRC_CC))
EXE_C:=$(basename $(SRC_C))
CC:=gcc

EXE:=$(EXE_CC) $(EXE_C)

.PHONY: all
all: $(EXE)

.PHONY: clean
clean:
	-@rm -f $(EXE)

#FLAGS=-Wall -Werror -O2 -s
#FLAGS:=-Wall -Werror -O2
FLAGS=-Wall -Werror -g3
CXXFLAGS:=$(FLAGS)
CXXFLAGS_NOOPT:=-Wall -Werror
CFLAGS:=$(FLAGS)
CFLAGS_NOOPT:=-Wall -Werror

# overriding general rules for specific targets that need extra libraries...

# C++ section...
# some wx constants
WX_CPP:=$(shell wx-config --cxxflags)
WX_LD:=$(shell wx-config --libs)
#WX_CPP=-I/usr/include/wx-2.8 -I/usr/lib/wx/include/gtk2-unicode-release-2.8
EXE_CC:=$(filter-out ./wxHello,$(EXE_CC))
wxHello: wxHello.cc
	$(CXX) $(CXXFLAGS) $(WX_CPP) $(WX_LD) -o $@ $<

GTK_CPP:=$(shell pkg-config --cflags gtk+-2.0)
GTK_LD:=$(shell pkg-config --libs gtk+-2.0)
EXE_CC:=$(filter-out ./hello_gtk,$(EXE_CC))
hello_gtk: hello_gtk.cc
	$(CXX) $(CXXFLAGS) $(GTK_CPP) -o $@ $< $(GTK_LD)
#	$(CXX) $(CXXFLAGS) -static $(GTK_CPP) -o $@ $< $(GTK_LD) -lX11 -lXcomposite -lXdamage -lXfixes -lXext -lXrender -lXinerama -lXi -lXrandr -lXcursor -lpcre -lxcb -lxcb-render -lxcb-render-util -lpthread -ltiff -lpng -lz -lpixman-1 -lselinux -ljasper -ljpeg -lXau -lXdmcp -lfreetype -lexpat -ldl

# C section...
EXE_C:=$(filter-out ./dialog,$(EXE_C))
dialog: dialog.c
	$(CC) $(CFLAGS) -o $@ $< /usr/lib/libdialog.a -lncursesw -lm
EXE_C:=$(filter-out ./backtrace,$(EXE_C))
backtrace: backtrace.c
	$(CC) $(CFLAGS) -rdynamic -o $@ $<

# general rules...
$(EXE_CC): %: %.cc
	$(CXX) $(CXXFLAGS) -o $@ $< `grep EXTRA_LIBS $< | cut -f 2 -d =`
$(EXE_C): %: %.c
	$(CC) $(CFLAGS) -o $@ $<

.PHONY: debug
debug:
	$(info EXE_CC is $(EXE_CC))
	$(info EXE_C is $(EXE_C))
