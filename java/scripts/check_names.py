#!/usr/bin/python3

# this script will remove all jars from .classpath files and will plant new
# ones according to jars in lib

import glob # for glob
import xml.etree.ElementTree # for ElementTree
import os.path # for split

projects_list=glob.glob('projects/*/.project')
debug=False

for project in projects_list:
	if debug:
		print('doing',project)
	(path,folder)=os.path.split(project)
	(path2,folder2)=os.path.split(path)
	if debug:
		print(folder2)
	document = xml.etree.ElementTree.ElementTree(file=project)
	counter=0
	for element in document.findall('./name'):
		if element.text!=folder2:
			raise ValueError("bad name for project "+project+" ("+folder2+" vs "+element.text+")")
		if debug:
			print(element.text)
		counter+=1
	assert counter==1
