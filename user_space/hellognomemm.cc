/* This is a demo program for using Gnome-- */

#include<gnome--.h>

int main(int argc,char** argv) {
	Gnome::Main application("mdi-test","1.0",argc,argv);
	Gnome::MDI* MDI_WINDOW=new Gnome::MDI("mdi-test","the great mdi test");
	MDI_WINDOW->open_toplevel();
	application.run();
	return(0);
}
