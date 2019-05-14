package pack;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	private String code;
	private List<Token> tokenList = new ArrayList<Token>();

	public Lexer(String code) {
		super();
		this.code = code;
	}
	
	public TokenBuffer tokenize() {
		
		// 하나씩 나눠진 code의 저장소
		String[] codeStorage = new String[1000];
		codeStorage = code.split("");
		
		for(String code : codeStorage) {
			if(code == "") {
				tokenList.add(code);
			}
		}
		
		for(TokenType code : TokenType.values()) {
			System.out.println(code.values());
		}
		
        
		return null;
	}
}