package meta.bookstore;

import java.io.Serializable;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
public class Book implements Serializable {

	private String title;
	private String author;
	private double price;

	public Book(String ititle, String iauthor, double iprice) {
		title = ititle;
		author = iauthor;
		price = iprice;
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

	public void setPrice(double iprice) {
		price = iprice;
	}

	public String toString() {
		return "BookDTO:" + title + " by:" + author + " price:" + price;
	}
}
