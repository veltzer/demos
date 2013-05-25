.PHONY: all
all:
	@echo tell me what to do...

.PHONY: check_imports
check_imports:
	@git grep import | grep \\*

.PHONY: checkstyle
checkstyle:
	@java -jar ~/install/checkstyle-5.6/checkstyle-5.6-all.jar -c support/checkstyle_config.xml -r projects/Standard/src
