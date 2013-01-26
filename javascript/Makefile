FOLDERS_GREP:=jquery core extjs jquery_controls
FOLDERS_CHECK:=jquery core jquery_controls
SOURCES_GREP:=$(shell find $(FOLDERS_GREP) -name "*.html" -or -name "*.js")
SOURCES_CHECK:=$(shell find $(FOLDERS_CHECK) -name "*.html" -or -name "*.js")

.PHONY: check
check:
	@~/install/jsl/jsl --conf=support/jsl.conf --quiet --nologo --nosummary --nofilelisting $(SOURCES_CHECK)

.PHONY: grep
grep:
	@-git grep "<br>" -- $(FOLDERS_GREP)
	@-git grep "type=\"text/javascript" -- $(FOLDERS_GREP)
	@-git grep "\"" -- $(SOURCES_GREP) | grep -v JSON
	@-git grep "DOCTYPE" -- $(SOURCES_GREP)
	@-git grep "  " -- $(SOURCES_GREP)
	@-git grep " + " -- $(SOURCES_GREP)
	@-git grep " = " -- $(SOURCES_GREP)
	@-git grep " - " -- $(SOURCES_GREP)
	@-git grep " > " -- $(SOURCES_GREP)
	@-git grep " < " -- $(SOURCES_GREP)
	@-git grep " $$" -- $(SOURCES_GREP)

.PHONY: count
count:
	@find . -name toolkits -prune -o -type f -and -name "*.js" -or -name "*.html" | wc -l

.PHONY: show
show:
	@find . -name toolkits -prune -o -type f -and -name "*.js" -or -name "*.html"

.PHONY: find_weird_files
find_weird_files:
	@find . -name toolkits -prune -o -type f -and -not -name "*.js" -and -not -name "*.html" -and -not -name "*.php" -and -not -name "*.txt" -and -not -name "*.json" -and -not -name "Makefile" -and -not -name "*.ajax" -and -not -name "*.ppt" -and -not -name "*.css" -and -not -name "*.pdf" -and -not -name "*.jpg" -and -not -name "*.png" -and -not -name ".gitignore" -and -not -name "*.odp" -and -not -name "*.htaccess" -and -not -name "*.pptx" -and -not -name "*.pps" -and -not -name "*.gif" -and -not -name "*.swf" -and -not -name "*.xml" -and -not -name "*.sh"

.PHONY: debug
debug:
	$(info SOURCES_GREP is $(SOURCES_GREP))
	$(info SOURCES_CHECK is $(SOURCES_CHECK))
