<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// pageContext < request < session < application
		// 현재			 요청+응답      세션만료        모든사용자
		String id = "jjunpro";
		String password = "1234";
		int age = 26;
		
		// 세션변수, 세션값
		session.setAttribute("id", id);
		session.setAttribute("password", password);
		session.setAttribute("age", age);
		
		out.println("세션에 값을 저장했습니다.");
	%>
	<a href="viewSession.jsp">세션 확인</a>
</body>
</html>