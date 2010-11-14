# directories
US_DIR:=cpp/user_space
KERNEL_DIR:=cpp/kernel
US_INCLUDE:=cpp/include

# kernel variables
KDIR:=/lib/modules/$(shell uname -r)/build

# do you want dependency on the makefile itself ?!?
DO_ALL_DEPS:=0

# compilation
#CXX:=g++
CXXFLAGS:=
# optimization with debug info (for disassembly)
DEBUG:=1
OPT:=1
ifeq ($(DEBUG),1)
CXXFLAGS:=$(CXXFLAGS) -g3
else
LDFLAGS:=$(LDFLAGS) -s
endif
ifeq ($(OPT),1)
CXXFLAGS:=$(CXXFLAGS) -O2
endif
#CODEGEN:=-g3
#CODEGEN:=-O2 -s
FLAGS:=-Wall -Werror -I$(US_INCLUDE)
CXXFLAGS:=$(CXXFLAGS) $(FLAGS)

# kernel module generation variables...
ifeq ($(DO_ALL_DEPS),1)
ALL_DEPS:=Makefile
else
ALL_DEPS:=
endif

# silent stuff
#.SILENT:
DO_MKDBG:=0
ifeq ($(DO_MKDBG),1)
Q:=
# we are not silent in this branch
else # DO_MKDBG
Q:=@
endif # DO_MKDBG

# sources from the git perspective
GIT_SOURCES:=$(shell git ls-files)
ALL:=
CLEAN:=
CLEAN_DIRS:=
CLEAN_EXTRA:=echo doing extra cleanup work

CC_SRC:=$(shell find $(US_DIR) $(KERNEL_DIR) -name "*.cc")
ALL_C:=$(shell find . -name "*.c")
ALL_CC:=$(shell find . -name "*.cc")
ALL_H:=$(shell find . -name "*.h")
ALL_HH:=$(shell find . -name "*.hh")
CC_ASX:=$(addsuffix .s,$(basename $(CC_SRC)))
CC_DIS:=$(addsuffix .dis,$(basename $(CC_SRC)))
CC_EXE:=$(addsuffix .exe,$(basename $(CC_SRC)))
ALL:=$(ALL) $(CC_EXE)
#ALL:=$(ALL) $(CC_DIS) $(CC_ASX)
CLEAN:=$(CLEAN) $(CC_EXE) $(CC_DIS) $(CC_ASX)

MOD_SRC:=$(shell find $(KERNEL_DIR) -name "drv_*.c" -and -not -name "drv_*.mod.c")
MOD_BAS:=$(basename $(MOD_SRC))
MOD_OBJ:=$(addsuffix .o,$(MOD_BAS))
MOD_SR2:=$(addsuffix .mod.c,$(MOD_BAS))
MOD_OB2:=$(addsuffix .mod.o,$(MOD_BAS))
MOD_CM1:=$(addprefix cpp/kernel/.,$(addsuffix .ko.cmd,$(notdir $(MOD_BAS))))
MOD_CM2:=$(addprefix cpp/kernel/.,$(addsuffix .mod.o.cmd,$(notdir $(MOD_BAS))))
MOD_CM3:=$(addprefix cpp/kernel/.,$(addsuffix .o.cmd,$(notdir $(MOD_BAS))))
MOD_MOD:=$(addsuffix .ko,$(MOD_BAS))
ALL:=$(ALL) $(MOD_MOD)
CLEAN:=$(CLEAN) $(MOD_MOD) $(MOD_SR2) $(MOD_OB2) cpp/kernel/Module.symvers cpp/kernel/modules.order $(MOD_CM1) $(MOD_CM2) $(MOD_CM3) $(MOD_OBJ)
CLEAN_DIRS:=$(CLEAN_DIRS) cpp/kernel/.tmp_versions

#### java section

JAVA_SOURCE_DIR:=java/src
JAVA_BIN:=java/bin
JAVA_SOURCES:=$(shell find $(JAVA_SOURCE_DIR) -name "*.java")
JAVA_COMPILE_STAMP:=java_compile.stamp
CLASSPATH:=java/lib/jdic.jar
ALL:=$(ALL) $(JAVA_COMPILE_STAMP)
CLEAN_DIRS:=$(CLEAN_DIRS)
CLEAN:=$(CLEAN) $(JAVA_COMPILE_STAMP)
CLEAN_EXTRA:=$(CLEAN_EXTRA); rm -rf $(JAVA_BIN)/{swing,extreme}

#### java section

#### python section

CLEAN_EXTRA:=$(CLEAN_EXTRA); find python -name "*.pyc" -exec rm {} \;

#### python section

.PHONY: all
all: $(ALL)

.PHONY: clean
clean: java_clean
	-$(Q)rm -f $(CLEAN)
	-$(Q)rm -rf $(CLEAN_DIRS)
	$(Q)$(CLEAN_EXTRA)
.PHONY: clean_git
clean_git:
	git clean -xdf

# kernel directory to build against
KDIR:=/lib/modules/$(shell uname -r)/build
# fill in the vervosity level you want for the kernel module compilation process
# V:=1 will give you the command lines used...
V:=0
# extra flags to pass to the kernel module creation process...
# regular kernels do not have -Werror and we want it!
KCFLAGS:=-Werror
KCFLAGS:=

# general rules...
# how to create regular executables...
$(CC_EXE): %.exe: %.cc $(ALL_DEPS)
	$(info doing [$@])
	$(Q)EXTRA_FLAGS=`./scripts/get_flags.pl $< $@`;\
	$(CXX) $(CXXFLAGS) -o $@ $< $$EXTRA_FLAGS
$(CC_ASX): %.s: %.cc $(ALL_DEPS)
	$(info doing [$@])
	$(Q)EXTRA_FLAGS=`./scripts/get_flags.pl $< $@`;\
	$(CXX) $(CXXFLAGS) -S -o $@ $< $$EXTRA_FLAGS
$(CC_DIS): %.dis: %.exe $(ALL_DEPS)
	objdump --source --disassemble $< > $@
# rule about how to create .ko files...
$(MOD_MOD): %.ko: %.c $(ALL_DEPS)
	$(info doing [$@])
	$(Q)$(MAKE) -C $(KDIR) V=$(V) KCFLAGS=$(KCFLAGS) M=$(abspath $(dir $<)) modules obj-m=$(addsuffix .o,$(notdir $(basename $<))) > /dev/null

.PHONY: debug
debug:
	$(info MOD_MOD is $(MOD_MOD))
	$(info CC_SRC is $(CC_SRC))
	$(info CC_DIS is $(CC_DIS))
	$(info CC_EXE is $(CC_EXE))
	$(info MOD_SRC is $(MOD_SRC))
	$(info ALL is $(ALL))
	$(info KDIR is $(KDIR))
	$(info V is $(V))
	$(info KCFLAGS is $(KCFLAGS))
	$(info CLEAN is $(CLEAN))
	$(info CLEAN_DIRS is $(CLEAN_DIRS))
	$(info GIT_SOURCES is $(GIT_SOURCES))

.PHONY: todo
todo:
	-@grep TODO $(CC_SRC)

# various checks...
.PHONY: check_ws
check_ws:
	-@grep "  " `find . -name "*.cc" -or -name "*.hh"`
.PHONY: check_files
check_files:
	-@find . -mindepth 2 -type f -and -not -name "*.cc" -and -not -name "*.h" -and -not -name "*.h" -and -not -name "*.txt" -and -not -name "*.conf" -and -not -name "*.ini" -and -not -name "*.sample" -and -not -name "*.data" -and -not -name "*.doc" -and -not -name "*.bash" -and -not -name "*.c"
.PHONY: check_include
check_include:
	-grep "include \"ace" `find . -name "*.cc" -or -name "*.h"`
.PHONY: check_tests_for_drivers
check_tests_for_drivers:
	cd kernel;for x in test_*.cc; do y=`echo $$x | cut -f 2- -d _`;z=drv_`basename $$y .cc`.c; if [ ! -f $$z ]; then echo "missing $$z"; fi ; done
	cd kernel;for x in drv_*.c; do y=`echo $$x | cut -f 2- -d _`;z=test_`basename $$y .c`.cc; if [ ! -f $$z ]; then echo "missing $$z"; fi ; done

# various file finds...
PROJECTS_EXPR:=-name ".project" -or -name ".cproject" -or -wholename "./nbproject/*"
SOURCE_EXPR:=-name "*.cc" -or -name "*.hh" -or -name "*.h" -or -name "*.c" -or -name "Makefile" -or -name "*.txt" -or -name "*.sed" -or -name "*.patch" -or -name "*.mk" -or -name "*.cfg" -or -name "*.sh" -or -name "*.cfg" -or -name "*.html" -or -name "*.css" -or -name "*.js" -or -name "*.ajax" -or -name "*.php" -or -name "*.gdb" -or -name ".gitignore" -or -name "*.pl" -or $(PROJECTS_EXPR) -or -name "*.java" -or -name "*.gif" -or -name "*.png" -or -name "*.xml" -or -name "*.sxw" -or -name "*.sxg" -or -wholename "*/.settings/*" -or -name "*.doc" -or -name "*.pdf" -or -name "*.jar" -or -name ".classpath" -or -name "*.sqlite" -or -name "*.py"
TARGET_EXPR:=-name "*.exe" -or -name "*.d" -or -name "*.o" -or -name "*.so" -or -name "*.o.cmd" -or -name "*.ko" -or -name "*.ko.cmd" -or -wholename "*/.tmp_versions/*" -or -name "Module.symvers" -or -name "modules.order" -or -name "*.class" -or -name "*.stamp" -or -name "*.dis"
GIT_SOURCE_EXPR:=-type f $(addprefix -or -path ./,$(GIT_SOURCES))

.PHONY: find_not_source
find_not_source:
	-@find -type f -not -path "./.git/*" -and -not \( $(SOURCE_EXPR) \)
.PHONY: find_not_target
find_not_target:
	-@find -type f -not -path "./.git/*" -and -not \( $(TARGET_EXPR) \)
.PHONY: find_not_source_target
find_not_source_target:
	-@find -type f -not -path "./.git/*" -and -not \( $(SOURCE_EXPR) \) -and -not \( $(TARGET_EXPR) \)
.PHONY: find_not_git_target
find_not_git_target:
	-@find -type f -and -not \( $(GIT_SOURCE_EXPR) \) -and -not \( $(TARGET_EXPR) \)
.PHONY: find_exercises
find_exercises:
	-@find -type f -name "*_exercise.txt"

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
.PHONY: do_astyle
do_astyle: $(ALL_DEPS)
	astyle --verbose --suffix=none --formatted --preserve-date --options=conf/astyle.cfg $(ALL_C) $(ALL_CC) $(ALL_H) $(ALL_HH)
# I do not use uncrustify because it changes code that it already beautified...
.PHONY: do_uncrustify
do_uncrustify:
	uncrustify -c conf/uncrustify.cfg --replace --no-backup $(ALL_C) $(ALL_CC) $(ALL_H) $(ALL_HH)

# java section

$(JAVA_COMPILE_STAMP): $(JAVA_SOURCES) $(ALL_DEPS)
	$(info doing [$@])
	$(Q)javac -classpath $(CLASSPATH) -d $(JAVA_BIN) -Xlint:unchecked $(JAVA_SOURCES)
	$(Q)touch $(JAVA_COMPILE_STAMP)

.PHONY: java_run
java_run: $(JAVA_COMPILE_STAMP) $(ALL_DEPS)
	$(info doing [$@])
	$(Q)java -classpath $(CLASSPATH):$(JAVA_BIN) extreme.profile.Main

.PHONY: java_prof
java_prof: $(JAVA_COMPILE_STAMP) $(ALL_DEPS)
	$(info doing [$@])
	$(Q)java -Xrunhprof:cpu=y -classpath $(JAVA_BIN) extreme.profile.Main

.PHONY: java_clean
java_clean: $(ALL_DEPS)
	$(info doing [$@])
	$(Q)rm -rf $(JAVA_BIN)/swing $(JAVA_BIN)/extreme $(JAVA_COMPILE_STAMP) java.hprof.txt

.PHONY: python_clean
python_clean: $(ALL_DEPS)
	$(Q)find python -name "*.pyc" -exec rm {} \;
