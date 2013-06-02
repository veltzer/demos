package programming.labs.lab1501networking_sol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class TestServer {
	private ServerSocket serverSocket;
	private List<Socket> sockets;

	public TestServer() {
		try {
			serverSocket = new ServerSocket(2525);
			sockets = new LinkedList<Socket>();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void accept() {
		Socket tempSocket;
		while (true) {
			try {
				tempSocket = serverSocket.accept();
				sockets.add(tempSocket);
				new Thread(new ClientManager(tempSocket)).start();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void finalize() {
		try {
			for (int i = 0; i < sockets.size(); i++) {
				sockets.get(i).close();
			}
			serverSocket.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/******** Inner Class **************************/
	private class ClientManager implements Runnable {
		private DataInputStream in;

		public ClientManager(Socket socket) {
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public void run() {
			String message = null;
			DataOutputStream out = null;

			while (true) {
				try {
					message = in.readUTF();
					for (int i = 0; i < sockets.size(); i++) {
						out = new DataOutputStream(
								((Socket) sockets.get(i)).getOutputStream());
						out.writeUTF(message);
					}

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/***********************************************/

	public static void main(String[] args) {
		TestServer test = new TestServer();
		test.accept();
	}
}
