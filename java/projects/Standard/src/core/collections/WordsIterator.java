package core.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * A demonstration for the use of the collection framework. This class
 * implements the Iterator interface.
 * @author Mark Veltzer <mark@veltzer.net>
 */

public class WordsIterator implements Iterator<String> {
	private String[] words;

	private int currentWord;

	/**
	 * Create the iterator using the given String. The iterator will break the
	 * string into words.
	 * @param input
	 */
	public WordsIterator(String input) {
		super();
		words = input.toLowerCase().split("[\\W]+");
		currentWord = 0;
	}

	/**
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return (currentWord < words.length);
	}

	/**
	 * @see java.util.Iterator#next()
	 */
	public String next() {
		String nextWord = words[currentWord];
		currentWord++;
		return nextWord;
		// Yes, I could also do:
		// return words[ nextWord++ ] ;

		// Actually, I should throw a NoSuchElementException in case
		// something goes wrong
	}

	/**
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new UnsupportedOperationException();
		// Not implemented
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("starting...");
		String s = "A thread-safe, mutable sequence of characters. A string buffer is like a String, "
				+ "but can be modified. At any point in time it contains some particular sequence of "
				+ "characters, but the length and content of the sequence can be changed through certain "
				+ "method calls.";

		Set<String> sortedWords = new TreeSet<String>(
				new StringsLengthComparator());
		Map<String, Integer> wordsCount = new HashMap<String, Integer>();

		for (Iterator<String> i = new WordsIterator(s); i.hasNext();) {
			String word = i.next();

			sortedWords.add(word);
			if (wordsCount.containsKey(word)) {
				int wordCount = wordsCount.get(word);
				wordsCount.put(word, wordCount + 1);
			} else {
				wordsCount.put(word, 1);
			}

		}
		System.out.println("********* sorted ************");
		for (String word : sortedWords) {
			System.out.println(word);
		}

		System.out.println("********* count ************");
		for (String word : wordsCount.keySet()) {
			System.out.println(word + ": " + wordsCount.get(word));
		}

		System.out.println("done...");
	}
}
