#include<ace/OS_NS_stdio.h>
#include<ace/Malloc_Base.h>
#include<ace/Message_Queue.h>
#include<ace/Read_Buffer.h>
#include<ace/streams.h>
#include<ace/Log_Msg.h>
#include<ace/SOCK_Connector.h>
#include<ace/INET_Addr.h>

/*
 * EXTRA_CMDS=pkg-config --cflags --libs ACE
 */

const int SIZE_BUF = 128;
const int NO_ITERATIONS = 5;

class Client {
public:
	ACE_SOCK_Stream client_stream_;
	ACE_INET_Addr remote_addr_;
	ACE_SOCK_Connector connector_;
	Client(char *hostname, int port) : remote_addr_(port, hostname) {
		// Nothing need to be done
	}


//Uses a connector component `connector_ to connect to a
//remote machine and pass the connection into a stream component client_stream_
	int connect_to_server() {
		// Initiate blocking connection with server.
		ACE_DEBUG((LM_DEBUG, "(%P|%t) Starting connect to %s:%d\n",remote_addr_.get_host_name(), remote_addr_.get_port_number()));
		if (connector_.connect(client_stream_, remote_addr_) == -1) {
			ACE_ERROR_RETURN((LM_ERROR, "(%P|%t) %p\n", "connection failed"), -1);
		} else {
			ACE_DEBUG((LM_DEBUG, "(%P|%t) connected to %s\n",remote_addr_.get_host_name()));
		}
		return(0);
	}


//Close down the connection properly.
	int close() {
		if (client_stream_.close() == -1) {
			ACE_ERROR_RETURN((LM_ERROR, "(%P|%t) %p\n", "close"), -1);
		} else {
			return(0);
		}
	}
};

int GetMessageType(char *data) {
	// Read new line from stdin
	static ACE_Read_Buffer rb(ACE_STDIN);

	// read a single line from stdin
	// Allocate a new buffer.
	char *buffer = rb.read('\n');

	// return message type zero when EOF is reached
	if (buffer == 0) {
		// Return 0 as message type
		return(0);
	} else {
		int type;
		sscanf(buffer, "%d", &type);
		ACE_OS::sprintf(data, "%s", buffer + 2);
		// Remove the type from the buffer
		return(type);
	}
}


int ACE_TMAIN(int argc, ACE_TCHAR *argv[]) {
	int FinalDelay = 4;

	if (argc < 2) {
		ACE_DEBUG((LM_DEBUG, "Usage %s <hostname> <port_number> [Final delay(sec)]\n", argv[0]));
		ACE_DEBUG((LM_DEBUG, "Where: <hostname> - may be localhost\n"));
		ACE_DEBUG((LM_DEBUG, "Final delay - optional delay time before program end\n"));
		ACE_OS::exit(1);
	}
	int port = ACE_OS::atoi(argv[2]);
	if (argc > 2) {
		int value = ACE_OS::atoi(argv[3]);
		if (value > FinalDelay) {
			FinalDelay = value;
		}
	}

	Client client(argv[1], port);

	client.connect_to_server();
	int type = 1;
	char buffer[100];

	while (type) {
		type = GetMessageType(buffer);
//		ACE_DEBUG((LM_DEBUG,"%s\n", buffer));
		if (client.client_stream_.send_n(buffer, ACE_OS::strlen(buffer) + 1, 0) == -1) {
			ACE_ERROR_RETURN((LM_ERROR, "(%P|%t) %p\n", "send_n"), 0);
		}
	}
	if (client.client_stream_.send_n("Quit", 5, 0) == -1) {
		ACE_ERROR_RETURN((LM_ERROR, "(%P|%t) %p\n", "send_n"), 0);
	}
	// Close down the connections
	// Let the other program complete the processing
	ACE_OS::sleep(FinalDelay);
	client.close();
}
