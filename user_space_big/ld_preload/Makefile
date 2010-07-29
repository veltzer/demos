ALL:=main.exe mainlink.exe libmylib.so

.PHONY: all
all: $(ALL)

mainlink.exe: main.cc libmylib.so
	g++ -o $@ $< -L. -lmylib
main.exe: main.cc
	g++ -o $@ $< -lm
libmylib.so: lib.cc
	g++ -fpic -shared -o $@ $<

.PHONY: clean
clean:
	-rm -f $(ALL) *.dis

.PHONY: run
run:
	LD_PRELOAD=./libmylib.so ./main.exe 0.5
.PHONY: runlink
runlink:
	LD_LIBRARY_PATH=. ./mainlink.exe 0.5

# rule about how to create disassembly files...
%.dis: %.exe
	objdump --source --disassemble $< > $@
