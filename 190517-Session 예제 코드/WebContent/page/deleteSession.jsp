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
		// 세션에 저장된 개별값 삭제
		session.removeAttribute("id");
		session.removeAttribute("age");
		
		// 세션 전체 삭제 초기화
		// session.invalidate();
	%>
	아이디 : ${sessionScope.id} <br/>
	나이 : ${sessionScope.age} <br/>
	비밀번호 : ${sessionScope.password} <br/>
	세션ID : <%=session.getId() %> 
</body>
</html>