package common;

import javax.servlet.http.Cookie;

public class Util {
	public String getCookie(Cookie[] cookies, String name) {
		String result = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				// 쿠키변수명이 같으면
				if(cookies[i].getName().equals(name)) {
					result = cookies[i].getValue(); // 쿠키값을 저장
					break;
				}
			}
		}
		
		return result;
	}
}
