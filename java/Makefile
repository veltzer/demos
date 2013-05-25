.PHONY: all
all:
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
	@java -jar ~/install/checkstyle-5.6/checkstyle-5.6-all.jar -c support/checkstyle_config.xml -r projects/Standard/src

.PHONY: format_eclipse
format_eclipse:
	~/install/eclipse-jee/eclipse -nosplash -application org.eclipse.jdt.core.JavaCodeFormatter -verbose -config support/org.eclipse.jdt.core.prefs `find . -type d -and -name src`
