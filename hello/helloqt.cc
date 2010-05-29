#include<QtGui/qapplication.h>
#include<QtGui/qpushbutton.h>

int main(int argc,char **argv)
{
	QApplication app(argc,argv);
	QPushButton hello("Hello, world!",0);
	QObject::connect(&hello,SIGNAL(clicked()),&app,SLOT(quit()));
	hello.show();
	return(app.exec());
}
