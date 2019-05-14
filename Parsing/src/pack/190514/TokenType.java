package pack;

public enum TokenType {
	T_DOOROPEN("[", true),
	T_DOORCLOSE("]", true),
	T_COMMA(",", true),
	T_STRING("'", true);

	private String regex;
	private boolean output;
	
	private TokenType(String regex, boolean output) {
		this.regex = regex;
		this.output = output;
	}

	public String getRegex() {
		return regex;
	}

	public boolean isOutput() {
		return output;
	}
}
