#!/usr/bin/python2.7

import qt,sys

a=qt.QApplication(sys.argv)
w=qt.QPushButton("Hello World",None)

# Here the differences to the trivial one above may be seen, using QT's signal and slot features:

# We connect the clicked() signal to the button's "close" slot (method).
# Whenever you click on the button, it emits the signal. Then every slot that is
# connected to that signal gets called. In this case, only w.close
w.connect(w,qt.SIGNAL("clicked()"),w.close)

# We make w the main widget of the application. That means that when w is closed
# The application exits. If we didn't do this, we would have to call a.quit()
# to make it end.
a.setMainWidget(w)
w.show()
a.exec_loop()
