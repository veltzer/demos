SOURCES:=$(shell find . -name "*.gpp")
OBJECTS:=$(addsuffix .out,$(basename $(SOURCES)))
ALL_DEPS:=Makefile

.PHONY: all
all: $(OBJECTS)

.PHONY: debug
debug:
	$(info SOURCES is $(SOURCES))
	$(info OBJECTS is $(OBJECTS))

$(OBJECTS): %.out: %.gpp $(ALL_DEPS)
	gpp -o $@ $<
