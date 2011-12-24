# parameters
SRC_DIR:=src/
OUT_DIR:=out/
# set up variables
SRC:=$(shell find $(SRC_DIR) -name "*.js")
OBJ:=$(addprefix $(OUT_DIR),$(notdir $(SRC)))
ALL:=$(OBJ)
# targets
.PHONY: all
all: $(ALL)
.PHONY: clean
clean:
	$(info doing [$@])
	rm -rf $(OUT_DIR)
.PHONY: debug
debug:
	$(info doing [$@])
	$(info SRC_DIR is $(SRC_DIR))
	$(info OUT_DIR is $(OUT_DIR))
	$(info SRC is $(SRC))
	$(info OBJ is $(OBJ))
# rules
$(OBJ): $(OUT_DIR)%: $(SRC_DIR)%
	$(info doing [$@])
	-mkdir $(dir $@) 2> /dev/null || exit 0
	yui-compressor $< -o $@
