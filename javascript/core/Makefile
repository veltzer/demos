SOURCES:=$(shell find . -name "*.html")

.PHNOY: check
check:
	@~/install/jsl/jsl --conf=../jquery/support/jsl.conf --quiet --nologo --nosummary --nofilelisting $(SOURCES)
	@git grep "<br>" -- examples exercises

.PHONY: debug
debug:
	$(info SOURCES is $(SOURCES))
