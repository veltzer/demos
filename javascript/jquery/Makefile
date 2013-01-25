SOURCES:=$(shell find . -name "*.html")

.PHNOY: check
check:
	@~/install/jsl/jsl --conf=support/jsl.conf --quiet --nologo --nosummary --nofilelisting $(SOURCES)


.PHONY: debug
debug:
	$(info SOURCES is $(SOURCES))
