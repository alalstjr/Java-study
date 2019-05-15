package lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.AnalyzerException;
import token.Token;
import token.TokenType;

/** Lexer 클래스는 Java 하위 집합에 대한 어휘 분석기를 나타냅니다. */

public class Lexer {
	
	/** 토큰 유형에서 정규 표현식으로의 매핑 */
	private Map<TokenType, String> regEx;

	/** 입력 소스에 나타나는 토큰 목록 */
	private List<Token> result;

	/**
	* 새로 생성 된 {@code Lexer} 객체를 초기화합니다.
	*/
	public Lexer() {
		regEx = new TreeMap<TokenType, String>();
		launchRegEx();
		result = new ArrayList<Token>();
	}

	/**
	* 입력 소스 코드의 토큰 화를 수행합니다.
	* 
	* @param source
	* 	분석 할 문자열
	* @throws AnalyzerException
	*	근원에 어휘 에러가있는 경우
	*/
	public void tokenize(String source) throws AnalyzerException {
		int position = 0;
		Token token = null;
		do {
			token = separateToken(source, position);
			System.out.println(token+"소스");
			if (token != null) {
				System.out.println("토큰 null");
				position = token.getEndIndex();
				result.add(token);
			}
		} while (token != null && position != source.length());
		
		System.out.println("while 밖");
		
		if (position != source.length()) {
			throw new AnalyzerException("위치 어휘 오류 # "+ position, position);
		}
	}
	
	/**
	* 일련의 토큰을 반환합니다.
	*
	* @return 토큰의리스트
	*/
	public List<Token> getTokens() {
		return result;
	}

	/**
	 * 형태가없는 토큰의 순서를 돌려줍니다.
	 * 주석이나  단일주석 공백 또는 스페이스바와 같은 형태가 없는 것
	 * {@code BlockComment},
	 * {@code LineComment} , {@code NewLine}, {@code Tab}, {@code WhiteSpace}
	 * 
	 * @return list of tokens
	 */
	public List<Token> getFilteredTokens() {
		List<Token> filteredResult = new ArrayList<Token>();
		for (Token t : this.result) {
			if (!t.getTokenType().isAuxiliary()) {
				filteredResult.add(t);
			}
		}
		return filteredResult;
	}
	
	/**
	* 특정 인덱스에서 소스를 스캔하고 첫 번째로 분리 된 값을 반환합니다.
	* token
	* 
	* @param source
	* 	스캔 할 소스 코드
	* @param fromIndex
	* 	검색을 시작할 인덱스
	* @return 토큰이 최초로 단락 지어진지, 토큰이 발견되지 않았던 경우는 {@code null}
	*
	*/
	private Token separateToken(String source, int fromIndex) {
		System.out.println(fromIndex);
		if (fromIndex < 0 || fromIndex >= source.length()) {
			throw new IllegalArgumentException("입력 스트림의 인덱스가 잘못되었습니다!");
		}
		for (TokenType tokenType : TokenType.values()) {
			
			// Pattern 에서의 fromIndex 는 검색할 대상의 위치라 생각하면 됩니다.
			// dotall 모드에서 표현식 . 은 어떠한 문자든이란 의미로 매칭된다. 
			// 마침 문자까지 합쳐서 말이다. 			
			Pattern p = Pattern.compile(".{" + fromIndex + "}" + regEx.get(tokenType),
					Pattern.DOTALL);
			
			Matcher m = p.matcher(source);
			//  source의 값이 저희가 선언한 패턴 p 에 맞는 조건인지 검사해서 m에 저장합니다.
			
			if (m.matches()) {
				String lexema = m.group(1);
				// group() (패턴에 맞는 값 1개씩 찾아내기)
				// group()을 하면 모두 반환하고
				// group(1)은 첫번째 sub group
				// group(2)는 두번째 sub group을 반환
				
				return new Token(fromIndex, fromIndex + lexema.length(), tokenType, lexema);
			}
		}

		return null;
	}
	
	/**
	* 토큰 유형에서 정규 표현식으로 맵을 작성합니다.
	*
	*/
	private void launchRegEx() {
		// 형태가 없는 정규식 토큰
		regEx.put(TokenType.BlockComment, "(/\\*.*?\\*/).*");
		regEx.put(TokenType.LineComment, "(//(.*?)[\r$]?\n).*");
		regEx.put(TokenType.WhiteSpace, "( ).*");
		regEx.put(TokenType.Tab, "(\\t).*");
		regEx.put(TokenType.NewLine, "(\\n).*");
		regEx.put(TokenType.String, "(\\').*");
		
		//	형태가 있는 정규식 토큰	
		regEx.put(TokenType.DOOROPEN, "(\\[).*");
		regEx.put(TokenType.DOORCLOSE, "(\\]).*");
		regEx.put(TokenType.COMMA, "(,).*");
		regEx.put(TokenType.Identifier, "\\b([a-zA-Z]{1}[0-9a-zA-Z_]{0,31})\\b.*");
		regEx.put(TokenType.IntConstant, "\\b(\\d{1,9})\\b.*");
		regEx.put(TokenType.False, "\\b(false)\\b.*");
		regEx.put(TokenType.True, "\\b(true)\\b.*");
		regEx.put(TokenType.Null, "\\b(null)\\b.*");
	}
}
