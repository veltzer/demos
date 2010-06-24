#include <ace/OS_NS_sys_time.h>
#include <ace/os_include/os_netdb.h>
#include <ace/Auto_Ptr.h>
#include <ace/Log_Msg.h>
#include <ace/INET_Addr.h>
#include <ace/SOCK_Acceptor.h>
#include <ace/Reactor.h>
#include <ace/Message_Queue.h>
#include <ace/SOCK_Stream.h>
#include <ace/Synch.h>


class ClientAcceptor:public ACE_Event_Handler {
	public:
		virtual ~ClientAcceptor();
		int open(const ACE_INET_Addr &listen_addr);
		virtual ACE_HANDLE get_handle (void) const {
			return this->acceptor_.get_handle ();
		}
		// Called when a connection is ready to accept.
		virtual int handle_input (ACE_HANDLE fd = ACE_INVALID_HANDLE);
		// Called when this handler is removed from the ACE_Reactor.
	//	virtual int handle_close (ACE_HANDLE handle,ACE_Reactor_Mask close_mask);
	protected:
		ACE_SOCK_Acceptor acceptor_;
};



ClientAcceptor::~ClientAcceptor() {
	this->handle_close (ACE_INVALID_HANDLE, 0);
}

int ClientAcceptor::open (const ACE_INET_Addr &listen_addr)
{ if (this->acceptor_.open (listen_addr, 1) == -1)
      ACE_ERROR_RETURN ((LM_ERROR,
                       ACE_TEXT ("%p\n"),
                       ACE_TEXT ("acceptor.open")),
                      -1);
ACE_OS::printf("connected\n");
  return this->reactor ()->register_handler
    (this, ACE_Event_Handler::ACCEPT_MASK);
}

int ClientAcceptor::handle_input (ACE_HANDLE)
{ /*ClientService *client;
  ACE_NEW_RETURN (client, ClientService, -1);
  auto_ptr<ClientService> p (client);

  if (this->acceptor_.accept (client->peer ()) == -1)
    ACE_ERROR_RETURN ((LM_ERROR,
                       ACE_TEXT ("(%P|%t) %p\n"),
                       ACE_TEXT ("Failed to accept ")
                       ACE_TEXT ("client connection")),
                      -1);
  p.release ();
  client->reactor (this->reactor ());
  if (client->open () == -1)
    client->handle_close (ACE_INVALID_HANDLE, 0);*/
	ACE_OS::printf("received\n");
	return 0;
}



int ACE_TMAIN (int, ACE_TCHAR *[])
{ ACE_INET_Addr port_to_listen (50000);
  ClientAcceptor acceptor;
  acceptor.reactor (ACE_Reactor::instance ());
  if (acceptor.open (port_to_listen) == -1)
    return 1;
	
  ACE_OS::printf("before event loop\n");
  ACE_Reactor::instance ()->run_reactor_event_loop ();
ACE_OS::printf("after event loop\n");
  return (0);
}