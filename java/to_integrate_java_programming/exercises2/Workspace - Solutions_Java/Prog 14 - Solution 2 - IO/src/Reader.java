import java.io.*;
public class Reader {
	private ObjectInputStream in;
	
	public Reader() throws IOException{
		in=new ObjectInputStream(new FileInputStream("c:/bank.ser"));
	}
	
		public Object read() throws ClassNotFoundException,IOException{
			return in.readObject();
		}
	
		public void close() throws IOException{
			in.close();
		}
}
