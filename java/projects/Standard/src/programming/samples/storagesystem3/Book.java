package programming.samples.storagesystem3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Book extends Item {
	private String bookname;
	private String author;

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
			e.printStackTrace();
		}

	}

	@Override
	public void toConsole() {
		super.toConsole();
		System.out.println("bookname: " + bookname);
		System.out.println("author: " + author);

	}

}