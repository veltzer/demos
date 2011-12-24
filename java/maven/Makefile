.PHONY: all
all:
	$(info tell me what to do...)
.PHONY: generate
generate:
	mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DgroupId=org.meta -DartifactId=mvn-app -DinteractiveMode=false
	cp Makefile mvn-app
.PHONY: compile
compile:
	mvn compile
.PHONY: test
test:
	mvn test
.PHONY: test-compile
test-compile:
	mvn test-compile
.PHONY: package
package:
	mvn package
.PHONY: install
install:
	mvn install
.PHONY: site
site:
	mvn site
.PHONY: clean
clean:
	mvn clean
.PHONY: idea
idea:
	mvn idea:idea
.PHONY: eclipse
eclipse:
	mvn eclipse:eclipse
.PHONY: seesite
seesite:
	gnome-open target/site/index.html
.PHONY: fullclean
fullclean:
	-rm -rf mvn-app
.PHONY: add-maven-repo
add-maven-repo:
	mvn -Declipse.workspace=$$HOME/workspace-maven eclipse:add-maven-repo
.PHONY: runeclipse
runeclipse:
	eclipse
.PHONY: debug
debug:
	echo $$HOME
.PHONY: siterun
siterun:
	mvn site:run
