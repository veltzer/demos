hello_world.class: hello_world.groovy
	groovyc hello_world.groovy

.PHONY: run
run: hello_world.class
	java -cp .:/usr/share/java/groovy-all.jar hello_world

.PHONY: clean
clean:
	rm -f hello_world.class
