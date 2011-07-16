import java.io.*;

public class Copy {
	private BufferedInputStream in;
	private BufferedOutputStream out;
	
	public Copy() throws IOException{
		in=new BufferedInputStream(new FileInputStream("c:/origin.txt"));
		out=new BufferedOutputStream(new FileOutputStream("c:/copy.txt"));
	}
	
	public void copyFile() throws IOException{
		int temp=in.read();
		while(temp!=-1){
			out.write(temp);
			temp=in.read();
		}
				
	}
	
	public void closeResources() throws IOException{
		in.close();
		out.close();
	}
	

	public static void main(String[] args) {
		try {
			Copy copy=new Copy();
			copy.copyFile();
			copy.closeResources();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
