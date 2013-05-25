package swing.keyboard;

import java.util.HashSet;
import java.util.Set;

public class HangManLogic {
	private String word;

	private Set<Character> guessedCharacters;

	public HangManLogic() {
		word = "";
		guessedCharacters = new HashSet<Character>();

	}

	public final void setWord(String iword) {
		word = iword;
		guessedCharacters = new HashSet<Character>();
		// What's missing here?
	}

	public String getWord() {
		return word;
	}

	public boolean characterIsGuessed(char c) {
		return guessedCharacters.contains(new Character(c));
	}

	/**
	 * @param c
	 * @return true when the guessed character is in the word.
	 */
	public boolean guessCharacter(char c) {
		guessedCharacters.add(new Character(c));

		return (word.indexOf(c) >= 0);
	}

	public char[] getCharacters() {
		return word.toCharArray();
	}

	public boolean guessComplete() {
		char[] characters = getCharacters();

		for (int i = 0; i < characters.length; ++i) {
			char c = characters[i];
			if (!guessedCharacters.contains(new Character(c))) {
				return false;
			}
		}
		return true;
	}
}
