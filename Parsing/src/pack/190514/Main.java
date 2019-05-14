package pack;

public class Main {

	public static void main(String[] args) {
		String str = new String();
		str = "['1a3',[null,false,['11',[112233],112],55, '99'],33, true]";
		
		Lexer lexer = new Lexer(str);
		TokenBuffer tokenBuffer = lexer.tokenize();
	}

}
