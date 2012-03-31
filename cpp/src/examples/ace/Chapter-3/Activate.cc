#include<ace/Task.h>
#include<ace/OS_NS_unistd.h>

/*
 * EXTRA_CMDS=pkg-config --cflags --libs ACE
 */

class HA_CommandHandler : public ACE_Task_Base {
public:
	virtual int svc(void) {
		ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%t) Handler Thread running\n")));
		ACE_OS::sleep(4);
		return(0);
	}
};

int ACE_TMAIN(int argc,ACE_TCHAR** argv,ACE_TCHAR** envp) {
	ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%t) Main Thread running\n")));

	HA_CommandHandler handler;
	int result = handler.activate();
	ACE_ASSERT(result == 0);

	ACE_UNUSED_ARG(result);

	handler.wait();
	return(0);
}
