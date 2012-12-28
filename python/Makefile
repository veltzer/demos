.PHONY: all
all: check_doublespace check_endspace check_eq

.PHONY: check_doublespace
check_doublespace:
	$(info doing $@)
	@-git grep -e "  " -- \*.py
.PHONY: check_endspace
check_endspace:
	$(info doing $@)
	@-git grep -e "\s$$" -- \*.py
.PHONY: check_eq
check_eq:
	$(info doing $@)
	@-git grep -e " = " -- \*.py
