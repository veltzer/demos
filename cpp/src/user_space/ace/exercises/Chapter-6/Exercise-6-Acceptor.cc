#include <ace/streams.h>
#include <ace/Log_Msg.h>
#include <ace/SOCK_Acceptor.h>
#include <ace/SOCK_Stream.h>
#include <ace/Message_Queue.h>
#include <ace/OS_NS_unistd.h>

/*
 * EXTRA_CMDS=pkg-config --cflags --libs ACE
 */

const int BUFFER_SIZE = 1024;

class Server {
public:
	Server(int port) :
		server_addr_(port), peer_acceptor_(server_addr_) {                                                                                                             //
	}


//Handle the connection once it has been established. Here the
//connection is handled by reading BUFFER_SIZE amount of data from the
//remote and then closing the connection stream down.
	int handle_connection() {                                                                                                                                                                                                                  // Read data from client
		static char message[BUFFER_SIZE + 10];                                                                                                                                                                                                         // Save some more space than being read

		while (1) {
			int byte_count = 0;
			if ((byte_count = new_stream_.recv(message, BUFFER_SIZE)) == -1) {
				ACE_ERROR((LM_ERROR, "%p\n", "Error in recv"));
			} else {
				message[byte_count] = 0;
				ACE_DEBUG((LM_DEBUG, "%s \n", message));
			}
			if (!ACE_OS::strcmp(message, "Quit")) {
				break;
			}
		}                                                                                                                                                                                                         // End while
// Close new endpoint
		if (new_stream_.close() == -1) {
			ACE_ERROR((LM_ERROR, "%p\n", "close"));
		}
		return(0);
	}


//Use the acceptor component peer_acceptor_ to accept the connection
//into the underlying stream new_stream_. After the connection has been
//established call the handle_connection() method.
	int accept_connections() {
		if (peer_acceptor_.get_local_addr(server_addr_) == -1) {
			ACE_ERROR_RETURN((LM_ERROR, "%p\n", "Error in get_local_addr"), 1);
		}
		ACE_DEBUG((LM_DEBUG, "Starting server at port %d\n", server_addr_.get_port_number()));
// Performs the iterative server activities.
		while (1) {
#define TIMEOUT
#ifdef TIMEOUT
			ACE_Time_Value timeout(ACE_DEFAULT_TIMEOUT);

			if (peer_acceptor_.accept(new_stream_, &client_addr_, &timeout) == -1) {
				ACE_ERROR((LM_ERROR, "%p\n", "accept"));
				continue;
			} else {
				ACE_DEBUG((LM_DEBUG, "Connection established with remote %s:%d\n",
				           client_addr_.get_host_name(), client_addr_.get_port_number()));
				handle_connection();                                                                                                                                                                                                                                                                                                                                                                                                                 //          Handle the connection
				ACE_OS::exit(1);
			}
#endif                                                                                                                                              /* TIMEOUT */
		}                                                                                                                                                                                                         // End while
	}


private:
//  char *data_buf_;
	ACE_INET_Addr server_addr_;
	ACE_INET_Addr client_addr_;
	ACE_SOCK_Acceptor peer_acceptor_;
	ACE_SOCK_Stream new_stream_;
};

int main(int argc, char *argv[]) {
	if (argc < 2) {
		ACE_ERROR((LM_ERROR, "Usage %s <port_num>\n", argv[0]));
		ACE_OS::exit(1);
	}
	Server server(ACE_OS::atoi(argv[1]));

	server.accept_connections();
}
