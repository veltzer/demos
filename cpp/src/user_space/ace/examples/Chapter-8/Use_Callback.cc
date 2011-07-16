#define ACE_NTRACE 0
#include <ace/Log_Msg.h>
#include "Callback.hh"

/*
 * EXTRA_CMDS=pkg-config --cflags --libs ACE
 */
int ACE_TMAIN(int, ACE_TCHAR *[]) {
	Callback *callback = new Callback;

	ACE_LOG_MSG->set_flags(ACE_Log_Msg::MSG_CALLBACK);
	ACE_LOG_MSG->clr_flags(ACE_Log_Msg::STDERR);
	ACE_LOG_MSG->msg_callback(callback);
	ACE_TRACE("main");
	ACE_DEBUG((LM_DEBUG, ACE_TEXT("%IHi Mom\n")));
	ACE_DEBUG((LM_INFO, ACE_TEXT("%IGoodnight\n")));
	return(0);
}
