ALL:=
CLEAN:=

SRC:=$(shell find . -name "*Test.php")
STAMP:=$(addsuffix .stamp,$(basename $(SRC)))

ALL:=$(ALL) $(STAMP)
CLEAN:=$(CLEAN) $(STAMP)

.SILENT:

.PHONY: all
all: $(ALL)

.PHONY: clean
clean:
	rm -f $(CLEAN)
	rm -rf myreport

# in order for the report to run you must have Xdebug installed...
# sudo apt-get install php5-xdebug
.PHONY: report
report:
	phpunit --coverage-html myreport .

# rules section

# rule how to run a test
$(STAMP): %.stamp: %.php
	$(info doing [$@])
	phpunit $< > /dev/null
	touch $@
