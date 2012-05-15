.PHONY: count
count:
	@find . -type f -and -name "*.js" -or -name "*.html" | wc -l

.PHONY: find_weird_files
find_weird_files:
	@find . -type f -and -not -name "*.js" -and -not -name "*.html" -and -not -name "*.php" -and -not -name "*.txt" -and -not -name "*.json" -and -not -name "Makefile"
