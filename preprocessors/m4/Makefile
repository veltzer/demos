SOURCES:=$(shell find . -name "*.m4")
OBJECTS:=$(addsuffix .out,$(basename $(SOURCES)))
ALL_DEPS:=Makefile

.PHONY: all
all: $(OBJECTS)

.PHONY: clean
clean:
	-rm -f $(OBJECTS)

.PHONY: debug
debug:
	$(info SOURCES is $(SOURCES))
	$(info OBJECTS is $(OBJECTS))

$(OBJECTS): %.out: %.m4 $(ALL_DEPS)
	m4 $< > $@
