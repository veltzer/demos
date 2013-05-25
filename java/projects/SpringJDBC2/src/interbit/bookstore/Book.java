package interbit.bookstore;

import java.io.Serializable;

import org.hibernate.annotations.Entity;

@SuppressWarnings("serial")
@Entity
public class Book implements Serializable {

	private String title;
	private String author;
	private double price;

	public Book(String title, String author, double price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public Book() {
	}

	/**
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return
	 */

	public String getTitle() {
		return title;
	}

	/**
	 * @param string
	 */
	public void setAuthor(String string) {
		author = string;
	}

	public void setTitle(String string) {
		title = string;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "BookDTO:" + title + " by:" + author + " price:" + price;
	}
}
