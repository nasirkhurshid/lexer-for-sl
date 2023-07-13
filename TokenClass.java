class TokenClass {
	private TOKEN token;
	private String value;

	public TokenClass(TOKEN t, String val) {
		token = t;
		value = val;
	}

	public void setToken(TOKEN t, String val) {
		token = t;
		value = val;
	}

	public TOKEN getToken() {
		return token;
	}

	public String getValue() {
		return value;
	}
}

enum TOKEN {
	IDENTIFIER, KEYWORD, INTEGERS, COMMENT, NUMERIC, 
	RELATION, ASSIGNMENT, LOGICAL, DELIMITER, WHITESPACE
}
