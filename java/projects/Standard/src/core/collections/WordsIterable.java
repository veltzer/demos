package core.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WordsIterable implements Iterable<String> {
	private String[] words;

	private class WordsIterator implements Iterator<String> {

		private int currentWord;

		public WordsIterator() {
			super();
			currentWord = 0;
		}

		public boolean hasNext() {
			return (currentWord < words.length);
		}

		public String next() {
			return words[currentWord++];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public WordsIterable(String input) {
		words = input.toLowerCase().split("[\\W]+");
	}

	public Iterator<String> iterator() {
		return new WordsIterator();
	}

	public static void main(String[] args) {
		System.out.println("starting...");
		String s = "A thread-safe, mutable sequence of characters. A string buffer is like a String, "
				+ "but can be modified. At any point in time it contains some particular sequence of "
				+ "characters, but the length and content of the sequence can be changed through certain "
				+ "method calls.";

		Set<String> sortedWords = new TreeSet<String>(
				new StringsLengthComparator());
		Map<String, Integer> wordsCount = new HashMap<String, Integer>();

		WordsIterable c = new WordsIterable(s);

		for (String word : c) {
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
