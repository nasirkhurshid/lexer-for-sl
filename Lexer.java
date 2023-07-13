import java.io.*;

public class Lexer {
	private int lineNumber;
	private TokenClass lastToken;
	FileReader reader;
	char lastCharacter;

	public Lexer(FileReader _reader) {
		FileReader reader = _reader;
		lineNumber = 1;
	}

	public TokenClass getToken() {
		try {
			int val;
			if (lastCharacter != '\0') {
				val = lastCharacter;
			} else {
				val = reader.read();
			}
			String value = Character.toString((char) val);
			if (val != -1) {
				if (val == '\n' || val == '\t' || val == '\r' || val == ' ') {
					if (val == '\n')
						lineNumber++;
					return new TokenClass(TOKEN.WHITESPACE, value);
				} else if (val == '#') {
					while (true) {
						val = reader.read();
						value += Character.toString((char) val);
						if (val == '\n' || val == -1) {
							lineNumber++;
							return new TokenClass(TOKEN.COMMENT, value);
						}
					}
				} else if (Character.isDigit(val)) {
					value = "";
					while (Character.isDigit(val)) {
						value += Character.toString((char) val);
						val = reader.read();
					}
					lastCharacter = (char) val;
					return new TokenClass(TOKEN.INTEGERS, value);
				} else if (Character.isAlphabetic(val)) {
					value = "";
					while (Character.isAlphabetic(val) || Character.isDigit(val)) {
						value += Character.toString((char) val);
						val = reader.read();
					}
					lastCharacter = (char) val;
					if (value.equals("if") || value.equals("else") || value.equals("while") || value.equals("break")
							|| value.equals("read") || value.equals("write") || value.equals("function")
							|| value.equals("let") || value.equals("call") || value.equals("return")) {
						return new TokenClass(TOKEN.KEYWORD, value);
					} else {
						return new TokenClass(TOKEN.IDENTIFIER, value);
					}
				} else {
					if (val == '+' || val == '-' || val == '*' || val == '/' || val == '%' || val == '~')
						return new TokenClass(TOKEN.NUMERIC, value);
					else if (val == '<' || val == '>') {
						val = reader.read();
						if (val == '=')
							value += Character.toString((char) val);
						else
							lastCharacter = (char) val;
						return new TokenClass(TOKEN.RELATION, value);
					} else if (val == '=') {
						val = reader.read();
						if (val == '=') {
							value += Character.toString((char) val);
							return new TokenClass(TOKEN.RELATION, value);
						} else {
							lastCharacter = (char) val;
							return new TokenClass(TOKEN.ASSIGNMENT, value);
						}
					} else if (val == '!') {
						val = reader.read();
						if (val == '=') {
							value += Character.toString((char) val);
							return new TokenClass(TOKEN.RELATION, value);
						} else {
							lastCharacter = (char) val;
							return new TokenClass(TOKEN.LOGICAL, value);
						}
					} else if (val == '|' || val == '&')
						return new TokenClass(TOKEN.LOGICAL, value);
					else if (val == '{' || val == '}' || val == '(' || val == ')' || val == ';' || val == ',')
						return new TokenClass(TOKEN.DELIMITER, value);
					else {
						System.err.println("Error: Unrecognized token at line " + lineNumber);
					}
				}
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void putBack(TokenClass token) {
		lastToken = token;
	}
}
