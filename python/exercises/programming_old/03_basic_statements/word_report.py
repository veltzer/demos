#!/usr/bin/python

# this is a solution to the word report exercies

# lets initialize the report dictionary...
report={}

# lets read all the lines in our own exercise...
f = open("word_report.py")
for line in f:
	for word in line.split():
		if report.has_key(word):
			report[word]+=1
		else:
			report[word]=1
f.close()
# lets print the report...
print report
