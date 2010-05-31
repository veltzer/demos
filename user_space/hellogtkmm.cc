#include<iostream>
#include<gtk--/button.h>
#include<gtk--/main.h>
#include<gtk--/window.h>

using std::cout;
using SigC::slot;

class HelloWorld:public Gtk::Window
{
	Gtk::Button m_button;
	public:
		HelloWorld();
		void hello();
		virtual int delete_event_impl(GdkEventAny *event);

};

void destroy_handler()
{
	Gtk::Main::quit();
}

HelloWorld::HelloWorld():Gtk::Window(GTK_WINDOW_TOPLEVEL),m_button("Hello World")
{
	destroy.connect(slot(&destroy_handler));
	m_button.clicked.connect(slot(this,&HelloWorld::hello));
	m_button.clicked.connect(destroy.slot());
	add(m_button);
	show_all();
}

void HelloWorld::hello()
{
	std::cout << "Hello, World!" << std::endl;
}

int HelloWorld::delete_event_impl(GdkEventAny *event)
{
	std::cout << "delete event occured" << std::endl;
	return(true);
}

int main(int argc,char** argv)
{
	Gtk::Main kit(argc,argv);
	HelloWorld helloworld;
	kit.run();
	return(0);
}

// unfortunate template stuff

template SigC::SlotData* SigC::manage<SigC::SlotData>(SigC::SlotData*);
