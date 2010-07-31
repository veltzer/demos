ALL:=
CLEAN:=

SRC:=$(shell find . -name "*.php")
STAMP:=$(addsuffix .stamp,$(basename $(SRC)))

ALL:=$(ALL) $(STAMP)
CLEAN:=$(CLEAN) $(STAMP)

.SILENT:

.PHONY: all
all: $(ALL)

.PHONY: clean
clean:
	rm -f $(CLEAN)

# rules section

# rule how to run a test
$(STAMP): %.stamp: %.php
	$(info doing [$@])
	phpunit $< > /dev/null
	touch $@
