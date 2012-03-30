#include<ace/config-lite.h>
#include<ace/OS_NS_time.h>
#include<ace/OS_NS_unistd.h>
#include<ace/Task.h>
#include<ace/Log_Msg.h>
#include<ace/Signal.h>
#include<ace/Sig_Handler.h>

/*
 * EXTRA_CMDS=pkg-config --cflags --libs ACE
 */

class SignalableTask : public ACE_Task<ACE_MT_SYNCH> {
public:
	virtual int handle_signal(int signum,
		siginfo_t * = 0,
		ucontext_t * = 0) {
		if (signum == SIGUSR1) {
			ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%t) received a %S signal\n"), signum));
			handle_alert();
		}
		return(0);
	}


	virtual int svc(void) {
		ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%t) Starting thread\n")));

		while (1) {
			ACE_Message_Block *mb = 0;
			ACE_Time_Value tv(0, 1000);

			tv += ACE_OS::time(0);
			int result = this->getq(mb, &tv);
			if ((result == -1) && (errno == EWOULDBLOCK)) {
				continue;
			} else {
				process_message(mb);
			}
		}

		ACE_NOTREACHED(return 0);
	}


	void handle_alert();
	void process_message(ACE_Message_Block *mb);
};
// Listing 1
void
SignalableTask::process_message(ACE_Message_Block *) {
}


void
SignalableTask::handle_alert() {
}


// Listing 2 code/ch13
int ACE_TMAIN(int, ACE_TCHAR *[]) {
	SignalableTask handler;

	handler.activate(THR_NEW_LWP | THR_JOINABLE, 5);

	ACE_Sig_Handler sh;
	sh.register_handler(SIGUSR1, &handler);

	ACE_OS::sleep(1);

	ACE_Thread_Manager::instance()->
	kill_grp(handler.grp_id(), SIGUSR1);
	handler.wait();
	return(0);
}
