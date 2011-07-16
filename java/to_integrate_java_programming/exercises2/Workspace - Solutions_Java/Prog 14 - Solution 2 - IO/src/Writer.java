import java.io.*;
public class Writer {
	private ObjectOutputStream out;
	
	public Writer() throws IOException{
		out=new ObjectOutputStream(new FileOutputStream("c:/bank.ser"));
	}
	
	public void write(Object o) throws IOException{
		out.writeObject(o);
	}
	
	public void close() throws IOException{
		out.close();
	}
}
