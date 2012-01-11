# parameters



SRC:=$(shell find . -name "*.asm")
OBJ:=$(addsuffix .o,$(basename $(SRC)))
EXE:=$(addsuffix .exe,$(basename $(SRC)))
ALL:=$(OBJ) $(EXE)

.PHONY: all
all: $(ALL)

.PHONY: debug
debug:
	$(info SRC is $(SRC))
	$(info OBJ is $(OBJ))
	$(info EXE is $(EXE))
.PHONY: clean
clean:
	-rm -rf $(ALL)

# rules
$(OBJ): %.o: %.asm
	nasm -f elf $< -o $@
$(EXE): %.exe: %.o
	ld -s -o $@ $<
