# This makefile shows how to use the .SILENT feature of the makefile...

DO_DEBUG=0

ifeq ($(DO_DEBUG),0)
.SILENT:
endif

all:
	echo Hello, World
	$(info and what about this)
