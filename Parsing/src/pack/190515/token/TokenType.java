package token;

// {@code TokeType} 열거 형은 Java 하위 집합의 토큰 유형을 나타냅니다.
// 언어

public enum TokenType {
	// 형태가 없는 토큰	 
	BlockComment,
	LineComment,
	WhiteSpace,
	Tab,
	NewLine,
	
	// 형태가 있는 토큰
	DOOROPEN,
	DOORCLOSE,
	COMMA,
	Identifier,
	IntConstant,
	False,
	True,
	Null,;
	// Identifier 식별자 자바에서 식별자는 클래스, 인터페이스,변수,메소드, 배열,문자열을 구분할수 있는 이름

	//	이 토큰이 보조(형태가 없는)인지 여부를 판별합니다.
	//	 
	//	@return 토큰이 보조 인 경우는 true, 그렇지 않은 경우는 false
	public boolean isAuxiliary() {
		return this == BlockComment || this == LineComment || this == NewLine || this == Tab
				|| this == WhiteSpace;
	}
}
