package pack;

public class ParsingView {

	public static void main(String[] args) {
		String str = new String();
		str = "['1a3',[null,false,['11',[112233],112],55, '99'],33, true]";
		
		ParsingController pc = new ParsingController();
		
		pc.parsing(str);
	}

}
