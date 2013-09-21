.PHONY: compile
compile:
	javac InlineMe.java

# this will run your class with profiliing turned on.
# you can see time measuremenets which shows how to do profiling
# using the standard Sun VM. You can also see if your methods are
# inlined by the jit or not since if you don't see them in the output
# they have been inlined.
.PHONY: run_profile
run_profile:
	java -Xrunhprof:cpu=times InlineMe
	cat java.hprof.txt  | grep Inline

.PHONY: run_jit_trace
run_jit_trace:
	_JIT_ARGS="trace" java InlineMe

.PHONY: clean
clean:
	-rm -f InlineMe.class java.hprof.txt *.stamp
