LLVMC:=llvmc-2.9

.PHONY: all
all: hello.exe hello.bc

hello.exe: hello.c
	$(LLVMC) hello.c -o hello.exe

hello.bc: hello.c
	$(LLVMC) -emit-llvm hello.c -c -o hello.bc

.PHONY: clean
clean:
	-rm -f hello.exe hello.bc
