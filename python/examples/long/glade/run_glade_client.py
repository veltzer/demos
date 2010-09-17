#!/usr/bin/env python

import sys
import pygtk
import gtk
import gtk.glade

class HellowWorldGTK:
	"""This is an Hello World GTK application"""

	def __init__(self):
		#Set the Glade file
		self.gladefile = "project1.glade"  
	        self.wTree = gtk.glade.XML(self.gladefile) 
		#Get the Main Window, and connect the "destroy" event
		self.window = self.wTree.get_widget("MainWindow")
		if (self.window):
			self.window.connect("destroy", gtk.main_quit)

if __name__ == "__main__":
	hwg = HellowWorldGTK()
	gtk.main()