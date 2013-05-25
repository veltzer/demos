JAVA_SOURCES:=$(shell find . -name "*.java")
JAVA_STAMP:=$(addsuffix .stamp,$(basename $(JAVA_SOURCES)))
ALL:=$(JAVA_STAMP)

.PHONY: all
all: $(ALL)
	@echo tell me what to do...

.PHONY: check_imports
check_imports:
	@git grep import | grep \\*

.PHONY: check_ws_eol
check_ws_eol:
	@git grep " $$" -- "*.java"

.PHONY: check_tab_eol
check_tab_eol:
	@git grep -l "\s$$" -- "*.java"

.PHONY: check_dbl_ws
check_dbl_ws:
	@git grep "  " -- "*.java"

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

# rules

$(JAVA_STAMP): %.stamp: %.java
	$(info doing [$@])
	@java -jar ~/install/checkstyle-5.6/checkstyle-5.6-all.jar -c support/checkstyle_config.xml $<
	@touch $@
