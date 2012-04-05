#!/usr/bin/python

import MySQLdb # for .connect
import sys # for .exit

try:
	conn=MySQLdb.connect(
		host='localhost',
		port=3306,
		user='',
		passwd='',
		db='mysql',
	);
	print 'conn is',conn
	with conn:
		cur=conn.cursor()
		cur.execute("SELECT VERSION()")
		data = cur.fetchone()
		print "Database version : %s " % data
except MySQLdb.Error, e:
	print "Error %d: %s" % (e.args[0],e.args[1])
	sys.exit(1)
