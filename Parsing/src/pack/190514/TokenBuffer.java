package pack;

import java.util.List;

public class TokenBuffer {
	private List<Token> tokenList;

	public TokenBuffer(List<Token> tokenList) {
		super();
		this.tokenList = tokenList;
	}

	public List<Token> getTokenList() {
		return tokenList;
	}
	
}
