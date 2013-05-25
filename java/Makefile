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
CLASSPATH:=$(shell scripts/get_classpath.py lib/*.jar)
CLASSPATH_CS:=$(CLASSPATH):$(HOME)/install/checkstyle-5.6/checkstyle-5.6-all.jar
MAINCLASS:=com.puppycrawl.tools.checkstyle.Main
ALL:=$(JAVA_STAMP)

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
	@scripts/ok_wrapper.pl git grep import | grep \\*

.PHONY: check_serialid
check_serialid:
	@scripts/ok_wrapper.pl git grep -l serialVersionUID -- "*.java"

.PHONY: check_ws_eol
check_ws_eol:
	@scripts/ok_wrapper.pl git grep " $$" -- "*.java"

.PHONY: check_tab_eol
check_tab_eol:
	@scripts/ok_wrapper.pl git grep -l "\s$$" -- "*.java"

.PHONY: check_dbl_ws
check_dbl_ws:
	@scripts/ok_wrapper.pl git grep "  " -- "*.java"

.PHONY: check_author
check_author:
	@scripts/ok_wrapper.pl git grep "@author" -- "*.java" | grep -v "Mark Veltzer"

.PHONY: check_version
check_version:
	@scripts/ok_wrapper.pl git grep -l "@version" -- "*.java"

.PHONY: checkstyle
checkstyle:
#	@java -jar ~/install/checkstyle-5.6/checkstyle-5.6-all.jar -c support/checkstyle_config.xml -r projects/Standard/src
	@java -jar ~/install/checkstyle-5.6/checkstyle-5.6-all.jar -c support/checkstyle_config.xml `find . -name "*.java"`

.PHONY: format_eclipse
format_eclipse:
	~/install/eclipse-jee/eclipse -nosplash -application org.eclipse.jdt.core.JavaCodeFormatter -verbose -config support/org.eclipse.jdt.core.prefs `find . -type d -and -name src`

.PHONY: debug
debug:
	$(info JAVA_SOURCES is $(JAVA_SOURCES))
	$(info JAVA_STAMP is $(JAVA_STAMP))
	$(info CLASSPATH is $(CLASSPATH))
	$(info HOME is $(HOME))

.PHONY: clean
clean:
	$(Q)rm -f $(ALL)

# rules

$(JAVA_STAMP): %.stamp: %.java
	$(info doing [$@])
	$(Q)java -cp $(CLASSPATH_CS) $(MAINCLASS) -c support/checkstyle_config.xml $<
	$(Q)touch $@

# code measurements

.PHONY: sloccount
sloccount:
	$(info doing [$@])
	@sloccount .
.PHONY: count_files
count_files:
	$(info doing [$@])
	@echo number of Java files: `find . -name "*.java" | wc -l`
