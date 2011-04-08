#!/usr/bin/python

import gtk
b = gtk.Button("Click me")
b.num=0
def callback(b):
	b.set_label("Hello, World!"+str(b.num))
	b.num+=1
b.connect('clicked', callback)
w = gtk.Window()
w.add(b) # put it inside the window
w.show_all() # must do this in GTK
gtk.main() # there's no way out of here
