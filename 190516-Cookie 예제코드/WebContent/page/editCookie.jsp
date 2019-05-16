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
		Cookie cookie1 = new Cookie("name", "change");
	
		response.addCookie(cookie1);
	%>
	이름 : ${cookie1.id.value} <br/>
	쿠키가 변경되었습니다.<br/>
	<a href="useCookie.jsp">쿠키 확인</a>
</body>
</html>