package main;

import exceptions.AnalyzerException;
import lexer.Lexer;

public class Main {

	public static void main(String[] args) {
		String sourceCode = "['1a3',[null,false,['11',[112233],112],55, '99'],33, true]";
		// String sourceCode = "['asd',]";
		
		Lexer lexer = new Lexer();
		try {
			lexer.tokenize(sourceCode);
		} catch (AnalyzerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
