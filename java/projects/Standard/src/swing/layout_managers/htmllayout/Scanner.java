package swing.layout_managers.htmllayout;

class Scanner {
	static final int LT = 0, GT = 1, EQ = 3, STR = 4, EOF = -1, ERROR = -2;

	private String currentString;
	private int lastTok;

	private boolean inTag;

	private String source;
	private int pos, end;

	Scanner(String isource) {
		setSource(isource);
		end = getSource().length();
	}

	int scanU() {
		int s = scan();
		if (s == STR) {
			setCurrentString(getCurrentString().toUpperCase());
		}

		return s;
	}

	int scan() {
		setLastTok(sscan());
		return getLastTok();
	}

	private int sscan() {
		while (true) {

			if (getPos() == end) {
				return EOF;
			}

			switch (getSource().charAt(getPos())) {
			case ' ':
			case '\t':
			case '\n':
			case '\r':
				setPos(getPos() + 1);
				continue;

			case '<':
				if (inTag) {
					return ERROR;
				} else {
					setPos(getPos() + 1);
					inTag = true;
					return LT;
				}

			case '>':
				if (!inTag) {
					return ERROR;
				} else {
					setPos(getPos() + 1);
					inTag = false;
					return GT;
				}

			case '=':
				if (inTag) {
					setPos(getPos() + 1);
					return EQ;
				}
				// else fall through

			default:
				return doString();
			}
		}
	}

	private int doString() {
		boolean usingQuote = inTag && getSource().charAt(getPos()) == '"';

		if (usingQuote) {
			setPos(getPos() + 1);
		}

		int start = getPos();

		char c;
		while (getPos() < end) {
			c = getSource().charAt(getPos());
			if (c == '>' || c == '<') {
				break;
			}

			if (inTag && c == '=') {
				break;
			}

			if (c == '"' && usingQuote) {
				setCurrentString(getSource().substring(start, getPos()));
				setPos(getPos() + 1);
				return STR;
			}

			if (inTag && !usingQuote && isWhitespace(c)) {
				break;
			}

			setPos(getPos() + 1);
		}

		setCurrentString(getSource().substring(start, getPos()));
		if (!inTag) {
			setCurrentString(getCurrentString().trim());
		}

		if (getCurrentString().length() == 0) {
			return scan();
		}

		return STR;
	}

	/* I'm avoiding Character.isWhitespace because it's JDK1.1 */
	private boolean isWhitespace(char c) {
		return c <= ' '
				&& (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f');
	}

	public String getCurrentString() {
		return currentString;
	}

	public void setCurrentString(String icurrentString) {
		currentString = icurrentString;
	}

	public int getLastTok() {
		return lastTok;
	}

	public void setLastTok(int ilastTok) {
		lastTok = ilastTok;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int ipos) {
		pos = ipos;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String isource) {
		source = isource;
	}
}
