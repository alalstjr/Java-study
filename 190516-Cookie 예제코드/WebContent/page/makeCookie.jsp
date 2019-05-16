<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<%
		// new Cookie("쿠키변수명", "값")
		Cookie cookie1 = new Cookie("name", "jjunPro");
		
		// Cookie cookie2 = new Cookie("age", 26);
		// Cookie는 무조건 String 값만 저장할 수 있습니다.
		Cookie cookie2 = new Cookie("age", "26");
		
		// 쿠키의 유효시간 설정(초단위)
		cookie1.setMaxAge(10);
		
		// 쿠키가 클라이언트에 저장됨
		response.addCookie(cookie1);
		response.addCookie(cookie2);
	%>
	
	쿠키가 생성되었습니다. <br/>
	<a href="useCookie.jsp?name=jjunpro">쿠키 확인</a>
</body>
</html>