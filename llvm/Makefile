LLVMC:=llvm-gcc
ALL:=hello.elf hello.bc

.PHONY: all
all: hello.elf
	$(info doing [$@])

hello.elf: hello.c
	$(info doing [$@])
	@$(LLVMC) $< -o $@

hello.bc: hello.c
	$(info doing [$@])
	@$(LLVMC) $< -c -o $@

.PHONY: clean
clean:
	$(info doing [$@])
	@rm -f $(ALL)

.PHONY: run
run:
	$(info doing [$@])
	@./hello.elf
