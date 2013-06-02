package programming.samples.storagesystem2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Book extends Item {
	private String bookname;
	private String author;

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String ibookname) {
		bookname = ibookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String iauthor) {
		author = iauthor;
	}

	@Override
	public void fromConsole() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("Enter book name: ");
			bookname = br.readLine();
			System.out.print("Enter author: ");
			author = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void toConsole() {
		super.toConsole();
		System.out.println("bookname: " + bookname);
		System.out.println("author: " + author);

	}

}
