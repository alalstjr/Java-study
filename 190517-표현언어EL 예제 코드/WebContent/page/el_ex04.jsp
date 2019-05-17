<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<!-- JAVA CODE -->
	<%
		session.setAttribute("name", "쭌프로");
		session.setAttribute("age", 26);
	%>
	
	<!-- EL CODE -->
	<!-- scope : page, request, session, application -->
	<c:set var="name" value="쭌프로" scope="session" />
	<c:set var="age" value="26" scope="session" />
	
	<a href="el_ex04_result.jsp">확인</a>
</body>
</html>