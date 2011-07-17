import java.net.*;
import java.io.*;
import java.util.*;

public class SentenceServer{
	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket=new ServerSocket(9999);
		System.out.println("Server started up on " + InetAddress.getLocalHost() + " port 9999");
		while (true){
			Socket socket= serverSocket.accept();
			InputStream in=socket.getInputStream();
			OutputStream out=socket.getOutputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(in, "latin1"));
			PrintWriter writer=new PrintWriter(new OutputStreamWriter(out,"latin1"));
			String sentence = reader.readLine();
			String reply = makeReply(sentence);
			writer.println(reply);
			writer.flush();
			socket.close();
		}
	}

	private static String makeReply(String sentence){
		StringTokenizer tok=new StringTokenizer(sentence);
		int count= tok.countTokens();
		String reply = "For '" + sentence + "' Word count is: " + count;
		return reply;
	}
}