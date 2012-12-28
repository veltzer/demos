.PHONY: all
all: check_doublespace check_endspace
.PHONY: check_doublespace
check_doublespace:
	$(info doing $@)
	@-git grep -e "  " -- \*.py
.PHONY: check_endspace
check_endspace:
	$(info doing $@)
	@-git grep -e "\s$$" -- \*.py
