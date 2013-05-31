package programming.samples.client_server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class SentenceClient {
	public static void main(String[] args) throws Exception {
		System.out.println("Connecting to server...");
		Socket socket = new Socket("localhost", 9999);

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				"latin1"));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,
				"latin1"));
		writer.println("good morning");
		writer.flush();
		String reply = reader.readLine();
		System.out.println("RECEIVED reply: " + reply);
		socket.close();
	}

}
