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

.PHONY: checkstyle
checkstyle:
	@java -jar ~/install/checkstyle-5.6/checkstyle-5.6-all.jar -c support/checkstyle_config.xml -r projects/Standard/src
