#include<ace/config-lite.h>
#include<ace/Synch.h>
#include<ace/Task.h>
#include<stdlib.h> // for EXIT_SUCCESS

/*
 * Mark Veltzer
 *
 * EXTRA_CMDS=pkg-config --cflags --libs ACE
 */

class HA_Device_Repository {
	public:
		HA_Device_Repository() {
		}
		void update_device(int device_id) {
			mutex_.acquire();
			ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%t) Updating device %d\n"),device_id));
			ACE_OS::sleep(1);
			mutex_.release();
			// Let the other thread to aquire too
			ACE_OS::sleep(1);
		}
	private:
		ACE_Thread_Mutex mutex_;
};
class HA_CommandHandler : public ACE_Task_Base {
	public:
		enum {
			NUM_USES = 10
		};
		HA_CommandHandler(HA_Device_Repository & rep) : rep_(rep) {
		}
		virtual int svc(void) {
			ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%t) Handler Thread running\n")));
			for (int i = 0; i < NUM_USES; i++) {
				this->rep_.update_device(i);
			}
			return(0);
		}
	private:
		HA_Device_Repository& rep_;
};

int ACE_TMAIN(int argc,ACE_TCHAR** argv,ACE_TCHAR** envp) {
	HA_Device_Repository rep;
	HA_CommandHandler handler1(rep);
	HA_CommandHandler handler2(rep);
	handler1.activate();
	handler2.activate();
	handler1.wait();
	handler2.wait();
	return EXIT_SUCCESS;
}
