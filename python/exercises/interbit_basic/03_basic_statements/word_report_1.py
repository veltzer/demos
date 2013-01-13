#!/usr/bin/python

# this is a solution to the word report exercies

# lets initialize the report dictionary...
report={}

# lets read all the lines in our own exercise...
# NOTE: the 'for' scope will close the file automagically...
for line in open('word_report_1.py'):
	for word in line.split():
		# next line is same as: if not word in report
		if not report.has_key(word):
			report[word]=0
		report[word]+=1
# lets print the report...
# NOTE: the with statement takes care of closing the file for us...
#with open('/tmp/report.txt','w') as f:
#	for word,count in report.items():
#		f.write("word {word} appeared {count} times\n".format(word=word,count=count))
#	#f.write(str(report))

with open('/tmp/report.txt','w') as f:
	for word in sorted(report.keys()):
		count=report[word]
		f.write("word {word} appeared {count} times\n".format(word=word,count=count))
	#f.write(str(report))
