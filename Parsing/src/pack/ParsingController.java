package pack;

public class ParsingController {
	
	SearchArray sa = new SearchArray();
	
	public void parsing(String str) {
		sa.search(str);
	}
	
	public void result() {
		System.out.println();
	}
}