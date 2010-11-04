#!/usr/bin/env python

INPUT_NAME = "tmp.txt"
OUTPUT_NAME = "tmp2.txt"

word_counts = {}

for word in open(INPUT_NAME).split():
    if word not in report:
        word_counts[c] = 0
    word_counts[c] += 1

f = open(OUTPUT, 'w')
for word, count in word_counts.items():
    f.write("%s appears %s times\n" % (word, count))
f.close()
    

