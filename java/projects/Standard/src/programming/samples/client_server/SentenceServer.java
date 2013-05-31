package programming.samples.client_server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public abstract class SentenceServer {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("Server started up on " + InetAddress.getLocalHost()
				+ " port 9999");
		boolean run = true;
		while (run) {
			Socket socket = serverSocket.accept();
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "latin1"));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,
					"latin1"));
			String sentence = reader.readLine();
			String reply = makeReply(sentence);
			writer.println(reply);
			writer.flush();
			socket.close();
		}
		serverSocket.close();
	}

	private static String makeReply(String sentence) {
		StringTokenizer tok = new StringTokenizer(sentence);
		int count = tok.countTokens();
		String reply = "For '" + sentence + "' Word count is: " + count;
		return reply;
	}
}
