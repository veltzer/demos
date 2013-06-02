#!/usr/bin/python3

# this script will remove all jars from .classpath files and will plant new
# ones according to jars in lib

import glob # for glob
import xml.dom.minidom # for parse
import xpath # for find

projects_list=glob.glob('projects/*/.project')

for project in projects_list:
	print('doing',project)
	document = xml.dom.minidom.parse(project)
	val=xpath.find('projectDescription/name', doc)
	print(val)
	"""
	counter=0
	for node in document.getElementsByTagName('name'):
		print(node.nodeValue)
		counter+=1
	assert counter==1
	"""
