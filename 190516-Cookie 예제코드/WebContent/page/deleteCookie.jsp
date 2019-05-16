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
		Cookie cookie = new Cookie("id", "");
		cookie.setMaxAge(0); // 즉시 삭제
		// cookie.setMaxAge(-1); // 브라우저를 닫을 때 삭제
		response.addCookie(cookie);		
	%>
	
	쿠키가 삭제되었습니다.
	<a href="useCookie.jsp">쿠키 확인</a>
</body>
</html>