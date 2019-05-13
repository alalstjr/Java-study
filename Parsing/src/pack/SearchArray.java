package pack;

import java.util.ArrayList;
import java.util.List;

public class SearchArray {
	public void search(String str) {
		
		// List 객체를 인스턴스 합니다.
		List<ParsingModel> list = new ArrayList<ParsingModel>();
		
		// 문자열 구분 변수
		String arrayList[] = new String[1000];
		arrayList = str.split(",");
		
		// List type, value 변수
		String type = new String();
		Object value = new String();
		
		String temp = new String();
		
		// 배열 검사
		for(String each : arrayList) {
			if(each.indexOf("[") > -1) {
				type = "ArrayOpen";
				value = "'['";
			} else if(each.indexOf("]") > -1) {
				type = "ArrayClose";
				value = "']'";
			} else if(each.indexOf("false") > -1) {
				type = "false";
				value = "false";
			} else if(each.indexOf("null") > -1) {
				type = "null";
				value = "null";
			} else if(each.indexOf("'") > -1) {
				type = "String";
				value = each;
			} else {
				type = "Number";
				value = Integer.parseInt(each);
			}
			
			ParsingModel pm = new ParsingModel(type, value);
			list.add(pm);
			
			System.out.println(each);
		}
		
		System.out.println(list);
		
	}
}
