.PHONY: all
all: embed

PYTHON_CFLAGS=$(shell python2.6-config --cflags)
PYTHON_LIBS=$(shell python2.6-config --libs)

embed: embed.c
	gcc -o $@ $< $(PYTHON_CFLAGS) $(PYTHON_LIBS)

.PHONY: clean
clean:
	rm -f embed
