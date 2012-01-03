#!/usr/bin/python

def lines2paragraphs(lines):
    """Group lines into paragraphs - as lists of lines."""
    paragraph = []
    for line in lines:
	line=line.strip()
        if line=='':
            yield paragraph
            paragraph = []
        else:
            paragraph.append(line)
    # The last paragraph remains
    # (if the input didn't end with an empty line)
    if len(paragraph)>0:
        yield paragraph

# Bonus: streaming lines->lines reformatter
# =========================================

import textwrap

def reformat(lines,chars):
    """Generate lines of reformatted paragraphs.

    Paragraph in input and output are separated by empty lines.
    """
    for para in lines2paragraphs(lines):
        for line in textwrap.wrap(' '.join(para),chars):
	    print line
        print

reformat(open('/usr/share/doc/coreutils/README.Debian'),72)
