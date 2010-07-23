SOURCES=$(shell find src -name "*.java")

compile: $(SOURCES) 
	javac -d bin -Xlint:unchecked $(SOURCES)
	touch compile

.PHONY: run
run: compile
	java -classpath bin extreme.profile.Main

.PHONY: prof
prof: compile
	java -Xrunhprof:cpu=y -classpath bin extreme.profile.Main

.PHONY: clean
clean:
	-rm -rf bin/* compile java.hprof.txt
