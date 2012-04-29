#!/bin/sh

find . -type f -and -not -name "*.html" -and -not -name "*.ajax" -and -not -name "*.php" -and -not -name "*.css" -and -not -name "*.js" -and -not -name "*.txt" -and -not -name ".gitignore" -and -not -name "*.sh" -and -not -name "*.pdf" -and -not -name "*.ppt"
grep "\ \ " `find . -type f -name "*.html"`
grep " $" `find . -type f -name "*.html"`
fgrep "<body" *.html | grep -v tundra
for x in *.html; do
	#xmllint --valid $x > /dev/null
	xmllint $x > /dev/null
done
