.PHONY: all
all: tools.stamp
	@true

tools.stamp: config/deps.py
	$(info doing [$@])
	@templar install_deps
	@pymakehelper touch_mkdir $@

.PHONY: clean
clean:
	@git clean -qffxd
