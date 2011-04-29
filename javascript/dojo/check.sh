#!/bin/sh

find . -not -name "*.html" -and -not -name "*.ajax" -and -not -name "*.php" -and -not -name "*.css" -and -not -name "*.js" -and -not -name "*.txt" -and -not -name ".gitignore" -and -not -name "*.sh" -and -type f
grep "\ \ " `find . -type f`
grep " $" `find . -type f`
