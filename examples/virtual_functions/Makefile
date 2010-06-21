SOURCES=$(shell find . -name "*.cc")
OBJECTS=$(addsuffix .o,$(basename $(SOURCES)))

.PHONY: all
all: main

main: $(OBJECTS)
	g++ -o $@ $^

.PHONY: clean
clean:
	-rm -f $(OBJECTS) 

.PHONY: debug
debug:
	$(info SOURCES is $(SOURCES))
	$(info OBJECTS is $(OBJECTS))

.PHONY: show
show:
	for x in *.o; do echo $$x; nm -C $$x | grep vtable ; done
