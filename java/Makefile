##############
# parameters #
##############
# do you want to show the commands executed ?
# Since we are using ?= for assignment it means that you can just
# set this from the command line and avoid changing the makefile...
DO_MKDBG?=0

#####################
# end of parameters #
#####################

JAVA_SOURCES:=$(shell find . -name "*.java")
JAVA_STAMP:=$(addsuffix .stamp,$(basename $(JAVA_SOURCES)))
CLASSPATH:=$(shell scripts/get_classpath.py lib/*.jar static/*.jar)
CLASSPATH_CS:=$(CLASSPATH)
MAINCLASS_CS:=com.puppycrawl.tools.checkstyle.Main
ALL:=$(JAVA_STAMP)
BIN_FOLDERS:=$(shell find . \( -name "bin" -or -name "build" -or -name "classes" -or -name "dist" \) -and -type d)

# silent stuff
ifeq ($(DO_MKDBG),1)
Q:=
# we are not silent in this branch
else # DO_MKDBG
Q:=@
#.SILENT:
endif # DO_MKDBG

.PHONY: all
all: $(ALL)

.PHONY: check_imports
check_imports:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep -e "import .*\.\*" -- "*.java"

.PHONY: check_serialid
check_serialid:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep -l serialVersionUID -- "*.java"

.PHONY: check_ws_eol
check_ws_eol:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep " $$" -- "*.java"

.PHONY: check_tab_eol
check_tab_eol:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep -l "\s$$" -- "*.java"

.PHONY: check_dbl_ws
check_dbl_ws:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep "  " -- "*.java"

.PHONY: check_author
check_author:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep -e "@author" --and --not -e "Mark Veltzer <mark@veltzer.net>" -- "*.java"

.PHONY: check_version
check_version:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep -l "@version" -- "*.java"

.PHONY: check_printstacktrace
check_printstacktrace:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep -l "printStackTrace()" -- "*.java"

.PHONY: check_exceptionnames
check_exceptionnames:
	$(info doing [$@])
	$(Q)scripts/ok_wrapper.pl git grep -l -e "throw new RuntimeException" --and --not -e "(e)" --and --not -e "ERR_STRING" -- "*.java"

.PHONY: check_src
check_src:
	$(info doing [$@])
	$(Q)scripts/check_src.sh

.PHONY: check_names
check_names:
	$(info doing [$@])
	$(Q)scripts/check_names.py

.PHONY: check_interbit
check_interbit:
	$(info doing [$@])
	$(Q)-git grep -l "interbit" | grep -v Makefile
	$(Q)-git grep -l "Interbit" | grep -v Makefile
	$(Q)-git grep -l "InterBit" | grep -v Makefile

.PHONY: check_all
check_all: check_imports check_serialid check_ws_eol check_tab_eol check_dbl_ws check_author check_version check_printstacktrace check_exceptionnames check_src check_names check_interbit

.PHONY: checkstyle
checkstyle:
	$(Q)java -jar lib/checkstyle-5.6-all.jar -c support/checkstyle_config.xml `find . -name "*.java"`

.PHONY: format_eclipse
format_eclipse:
	$(Q)~/install/eclipse-jee/eclipse -nosplash -application org.eclipse.jdt.core.JavaCodeFormatter -verbose -config support/org.eclipse.jdt.core.prefs `find . -type d -and -name src`

.PHONY: build
build:
	$(Q)~/install/eclipse-jee/eclipse -noSplash -data ~/workspace-jee -application org.eclipse.jdt.apt.core.aptBuild

.PHONY: debug
debug:
	$(info JAVA_SOURCES is $(JAVA_SOURCES))
	$(info JAVA_STAMP is $(JAVA_STAMP))
	$(info CLASSPATH is $(CLASSPATH))
	$(info HOME is $(HOME))
	$(info BIN_FOLDERS is $(BIN_FOLDERS))
	$(info CLASSPATH_CS is $(CLASSPATH_CS))
	$(info MAINCLASS_CS is $(MAINCLASS_CS))

.PHONY: clean
clean:
	$(Q)rm -f $(ALL)
	$(Q)rm -rf $(BIN_FOLDERS)

# rules

$(JAVA_STAMP): %.stamp: %.java
	$(info doing [$@])
	$(Q)java -cp $(CLASSPATH_CS) $(MAINCLASS_CS) -c support/checkstyle_config.xml $<
	$(Q)touch $@

# code measurements

.PHONY: sloccount
sloccount:
	$(info doing [$@])
	$(Q)sloccount .

.PHONY: count_files
count_files:
	$(info doing [$@])
	$(Q)echo number of Java files: `find . -name "*.java" | wc -l`
