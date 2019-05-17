<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- JAVA CODE -->
	<h2>JAVA CODE 방식</h2>
	<%
	String name=(String)session.getAttribute("name");
	int age = Integer.parseInt((String)session.getAttribute("age"));
	%>
	
	이름 : <%=name %> <br/>
	나이 : <%=age %> <br/>
	
	<!-- EL CODE -->
	<h2>EL 방식</h2>
	이름 : ${sessionScope.name} <br/>
	나이 : ${sessionScope.age} <br/>	
</body>
</html>