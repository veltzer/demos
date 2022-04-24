.PHONY: all
all: tools.stamp
	@true

tools.stamp: config/deps.py
	$(info doing [$@])
	@pymakehelper touch_mkdir $@

.PHONY: clean
clean:
	@rm tools.stamp

.PHONY: clean_hard
clean_hard:
	@git clean -qffxd
