import java.net.*;
import java.io.*;

public class SentenceClient{
	public static void main(String[] args) throws Exception{
		System.out.println("Connecting to server...");
		Socket socket=new Socket("localhost", 9999);

		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(in, "latin1"));
		PrintWriter writer=new PrintWriter(new OutputStreamWriter(out,"latin1"));
		writer.println("good morning");
		writer.flush();
		String reply = reader.readLine();
		System.out.println("RECEIVED reply: " + reply);
		socket.close();
	}

}