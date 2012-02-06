.PHONY: all
all: hello.exe hello.bc

hello.exe: hello.c
	llvm-gcc hello.c -o hello.exe

hello.bc: hello.c
	llvm-gcc -emit-llvm hello.c -c -o hello.bc

.PHONY: clean
clean:
	-rm -f hello.exe hello.bc
