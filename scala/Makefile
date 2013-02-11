SOURCES:=$(shell find . -name "*.scala")
STAMP:=compile.stamp

.PHONY: all
all: $(STAMP)
	$(info doing $@)


$(STAMP): $(SOURCES)
	scalac $(SOURCES)
	touch $@

.PHONY: debug
debug:
	$(info doing $@)
	$(info SOURCES is $(SOURCES))
	$(info STAMP is $(STAMP))

.PHONY: run
run:
	$(info doing $@)
	scala HelloWorld

.PHONY: clean
clean:
	$(info doing $@)
	rm -f `find . -name "*.class"` $(STAMP)
