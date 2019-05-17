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
	<%-- ${변수 or 식} --%> 
	${2 + 4}<br/>
	${2 / 4}<br/>
	${2 mod 4}<br/> <!-- 나머지 -->
	${2 < 4}<br/>
	${3.1 le 3.2}<br/> <!-- less 작다 -->
	${(5 > 3) ? 5 : 3}<br/> <!-- 삼항연산자 -->
</body>
</html>

