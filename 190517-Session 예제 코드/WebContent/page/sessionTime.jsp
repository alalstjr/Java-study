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
	<h2>세션의 유효시간 설정</h2>
	<%
		// 세션의 유효시간 변경
		session.setMaxInactiveInterval(1200);
	
		// 세션의 유효시간이 몇초인지 알려주는 메서드 setMaxInactiveInterval
		// 톰켓 기본값 : 1800초(30분)
		// session.setMaxInactiveInterval(600); // 10분
		int timeout = session.getMaxInactiveInterval();
		out.println("세션의 유효시간 : " + timeout); // 초 단위
	%>
	
</body>
</html>