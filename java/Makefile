SOURCES=$(shell find src -name "*.java")

compile.stamp: $(SOURCES) 
	javac -classpath lib/jdic.jar -d bin -Xlint:unchecked $(SOURCES)
	touch compile.stamp

.PHONY: run
run: compile.stamp
	java -classpath lib/jdic.jar:bin extreme.profile.Main

.PHONY: prof
prof: compile.stamp
	java -Xrunhprof:cpu=y -classpath bin extreme.profile.Main

.PHONY: clean
clean:
	-rm -rf bin/* compile.stamp java.hprof.txt
