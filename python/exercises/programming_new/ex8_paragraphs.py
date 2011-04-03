#!/usr/bin/env python

def lines2paragraphs(lines):
    """Group lines into paragraphs - as lists of lines."""
    paragraph = []
    for line in lines:
        if not line.strip():
            yield paragraph
            paragraph = []
        else:
            paragraph.append(line)
    # The last paragraph remains
    # (if the input didn't end with an empty line)
    if paragraph:
        yield paragraph

# Bonus: streaming lines->lines reformatter
# =========================================

import textwrap

def reformat(lines):
    """Generate lines of reformatted paragraphs.

    Paragraph in input and output are separated by empty lines.
    """
    for p in lines2paragraphs(lines):
        for lines in textwrap.wrap(''.join(p),40):
	    print lines
        print

# test
if __name__ == '__main__':
    #print list(reformat(['foo', 'bar', 'baz', '', 'quux', 'quuux']))
    print ''.join(reformat(open('README.Debian')))
