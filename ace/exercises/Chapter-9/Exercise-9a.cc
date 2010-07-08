//  We must replace Acceptor with Connector
// #define ACE_NTRACE 0
#include <ace/config-lite.h>
#include <ace/OS_NS_unistd.h>
#include <ace/Task.h>
#include <ace/Log_Msg.h>
#include <ace/Process_Manager.h>

#include <ace/OS_NS_time.h>
#include <ace/Signal.h>
#include <ace/Sig_Handler.h>
#include <ace/INET_Addr.h>
#include <ace/SOCK_Stream.h>
#include <ace/SOCK_Acceptor.h>
#include <ace/SOCK_Connector.h>

/*
 * EXTRA_CMDS=pkg-config --cflags --libs ACE
 */

#define NCHILDREN 2
int MyIndex = -1;
long ListenPort;

class SignalableTask : public ACE_Task<ACE_MT_SYNCH> {
public:
	virtual int handle_signal(int signum, siginfo_t * = 0, ucontext_t * = 0) {
		if (signum == SIGUSR1) {
			ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%t) received a %S signal\n"), signum));
			MyIndex = 0;
//      handle_alert ();
		}
		if (signum == SIGUSR2) {
			ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%t) received a %S signal\n"), signum));
			MyIndex = 1;
//      handle_alert ();
		}
		return(0);
	}


	void handle_alert();
};

void SignalableTask::handle_alert() {
}


int DoAccept(long ReceivePort, ACE_SOCK_Stream *peer, ACE_INET_Addr *peer_addr, ACE_SOCK_Acceptor *acceptor) {
	ACE_Time_Value timeout(10, 0);

	ACE_INET_Addr address_to_listen = ACE_INET_Addr(ReceivePort, ACE_LOCALHOST);

	if (acceptor->open(address_to_listen, 1) == -1) {                                                                                                      // Use the address as well as re-use flag
		ACE_ERROR_RETURN((LM_ERROR, ACE_TEXT("%p\n"), ACE_TEXT("acceptor.open")), 100);
	}

	ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) Trying to accept %d.\n"), ReceivePort));
	if (acceptor->accept(*peer, peer_addr, &timeout, 0) == -1) {
		if (ACE_OS::last_error() == EINTR) {
			ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) Interrupted while ")
			           ACE_TEXT("waiting for connection\n")));
		} else if (ACE_OS::last_error() == ETIMEDOUT) {
			ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) Timeout while ")
			           ACE_TEXT("waiting for connection\n")));
		}
	} else {
		ACE_TCHAR peer_name[MAXHOSTNAMELEN];
		peer_addr->addr_to_string(peer_name, MAXHOSTNAMELEN);
		ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) Connection from %s\n"), peer_name));
	}
	ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) accept Done %d.\n"), ReceivePort));
	return(0);
}


int ReceiveMessages(ACE_SOCK_Stream peer[], ACE_SOCK_Acceptor acceptor[]) {
	char buffer[4096];

	int socket_fd, result;
	fd_set readset;

	socket_fd = acceptor[1].get_handle();                                                                                                                      /* The second handle is the highest one */

	for (int count = 0; count < NCHILDREN; count++) {                                                                                                          // We are waiting for NCHILDREN messages one from each child
		do {
			FD_ZERO(&readset);                                                                                                                                                                                                                                                                                                                          // Zero the bit list
			for (int i = 0; i < socket_fd + 1; i++) {
				FD_SET(i, &readset);                                                                                                                                                                                                                                                                                                                                                                                                                                                         // Set the appropriate fd
			}
			result = select(socket_fd + 1, &readset, NULL, NULL, NULL);                                                                                                                                                                                                                                                                                                                                                                                       // Wiat for input
		} while (result == -1 && errno == EINTR);

		if (result > 0) {
			for (int i = 0; i < socket_fd; i++) {                                                                                                                                                                                                                                                                                                                // Loop on all bits and find one
				if (FD_ISSET(i, &readset)) {
					result = peer[count].recv(buffer, sizeof(buffer));
					ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) R E C E I V I N G: <%s>\n"), buffer));
					if (result == 0) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       /* This means the other side closed the socket */
						ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) result=0 for count: %d\n"), count));
						peer[count].close();
					} else {
						peer[count].send_n("OK", 3, 0);
					}
				}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                // if FD_ISSET
			}                                                                                                                                                                                                                                                                                                                                                                                                                                                     // End of for loop
		}
	}                                                                                                                                                                                                                                                                                                                                                                                               /* End of for count loop */
	// Close both ports
	peer[0].close();
	peer[1].close();
	return(0);
}


int ACE_TMAIN(int argc, ACE_TCHAR *argv[]) {
	SignalableTask handler;

	if (argc < 3) {
		ACE_DEBUG((LM_DEBUG, ACE_TEXT("Usage: %s Port1 Port2\n"), argv[0]));
		return(1);
	}
	if (argc > 3) {                                                                                                       // Running as a child.
		ACE_Sig_Handler sh;
		ACE_SOCK_Stream peer;
		ACE_SOCK_Connector connector;
		char buffer[4096];
		ACE_Time_Value timeout(10, 0);

		sh.register_handler(SIGUSR1, &handler);                                                                                                                                                                                                            // Use SIGUSR1
		sh.register_handler(SIGUSR2, &handler);                                                                                                                                                                                                            // Use SIGUSR2
		// wait untill MyIndex is modified
		while (MyIndex == -1) {
			ACE_OS::sleep(1);
		}

		ListenPort = atoi(argv[MyIndex + 1]);

		ACE_INET_Addr address_to_listen = ACE_INET_Addr(ListenPort, ACE_LOCALHOST);
		ACE_OS::sleep(1);
		if (-1 == connector.connect(peer, address_to_listen, &timeout)) {
			ACE_DEBUG((LM_DEBUG, ACE_TEXT("%p\n"), ACE_TEXT("(%P|%t) Client %d connect"), MyIndex + 1));
			return(1);
		}
		sprintf(buffer, "I'm Child %d This is My Message.", MyIndex + 1);
		size_t size = ACE_OS::strlen(buffer);
		ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) Child %d   sending: <%s>\n"), MyIndex + 1, buffer));
		ACE_OS::sleep(2);                                                                                                                                                                                                                             // Wait some time before sending a message
		peer.send_n(buffer, size, 0);
		peer.recv(buffer, sizeof(buffer));                                                                                                                                                                                                            // Get Acknowledge
		peer.close();
		ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) Child %d is terminating.\n"), MyIndex + 1));
	} else {                                                                                                             // Running as a parent.
		ACE_Process_Manager *pm = ACE_Process_Manager::instance();
		// Get the processwide process manager.
		ACE_SOCK_Stream peer[NCHILDREN];
		ACE_INET_Addr peer_addr[NCHILDREN];
		ACE_SOCK_Acceptor acceptor[NCHILDREN];

		// Specify the options for the new processes to be spawned.
		ACE_Process_Options options;
		options.command_line(ACE_TEXT("%s %s %s Extra"), argv[0], argv[1], argv[2]);

		// Spawn two child processes.
		pid_t pids[NCHILDREN];
		pm->spawn_n(NCHILDREN, options, pids);

		ACE_OS::kill(pids[0], SIGUSR1);                                                                                                                                                                                                             // 1st process will use the 1st port
		ACE_OS::kill(pids[1], SIGUSR2);                                                                                                                                                                                                             // 2nd process will use the 2nd port

		// Wait for the child we just terminated.
		long ListenPort1 = atoi(argv[1]);
		long ListenPort2 = atoi(argv[2]);
		ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) Parent Ports are: %d %d\n"), ListenPort1, ListenPort2));
		// Do the messaging with 1st Process
		DoAccept(ListenPort1, &peer[0], &peer_addr[0], &acceptor[0]);
		DoAccept(ListenPort2, &peer[1], &peer_addr[1], &acceptor[1]);
		ReceiveMessages(peer, acceptor);
		// Wait for all the children to exit.
		pm->wait(0);
		ACE_DEBUG((LM_DEBUG, ACE_TEXT("(%P|%t) Parent is terminating.\n")));
	}
	return(0);
}
