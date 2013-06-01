#!/usr/bin/python3

# this script will remove all jars from .classpath files and will plant new
# ones according to jars in lib

import glob # for glob
import xml.dom.minidom # for parse

raise ValueError("you really should use eclipse variables and not this script")

addJars=False
jar_list=glob.glob('lib/*.jar')
jar_list.extend(glob.glob('static/*.jar'))
classpath_list=glob.glob('projects/*/.classpath')

def iterate_children(parent):
	child = parent.firstChild
	while child != None:
		yield child
		child = child.nextSibling

for filename in classpath_list:
	document = xml.dom.minidom.parse(filename)
	# remove all classpathentry elements with attribute kind=lib
	for node in document.getElementsByTagName('classpathentry'):
		if node.getAttribute('kind')=='lib':
			node.parentNode.removeChild(node)
	# add all the jars
	if addJars:
		for jar in jar_list:
			e=document.createElementNS(None,'classpathentry')
			e.setAttribute('kind','lib')
			e.setAttribute('path','../../'+jar)
			document.firstChild.appendChild(e)
	# remove white space
	remove_list=[]
	for child in iterate_children(document.firstChild):
		if child.nodeType==child.TEXT_NODE:
			value=child.nodeValue
			if value.strip()=="":
				remove_list.append(child)
	for node in remove_list:
		node.parentNode.removeChild(node)
	# write the output file
	with open(filename,"w") as f:
		f.write(document.toprettyxml(indent="\t"))
