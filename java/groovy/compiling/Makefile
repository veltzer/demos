SOURCES=$(shell find . -name "*.groovy")
OBJECTS=$(addsuffix .class,$(basename $(SOURCES)))
STAMP=compile.stamp
CLASS=hello_world

.PHONY: all
all: compile

.PHONY: compile
compile: $(STAMP)

$(STAMP): $(SOURCES)
	groovyc $(SOURCES)
	touch $(STAMP)

.PHONY: run
run: compile
	java -cp .:/usr/share/java/groovy-all.jar $(CLASS)

.PHONY: clean
clean:
	rm -f $(OBJECTS) $(STAMP)

.PHONY: debug
debug:
	$(info SOURCES is $(SOURCES))
	$(info OBJECTS is $(OBJECTS))
