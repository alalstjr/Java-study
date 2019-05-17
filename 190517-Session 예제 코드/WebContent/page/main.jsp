<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/session_check.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btnLogout").on("click",function(){
			// 페이지 이동
			location.href = "${path}/session_servlet/logout.do";
		});
	});
</script>
</head>
<body>
	<!-- JAVA 코드 -->
	<!-- <h2>%=session.getAttribute("message") %></h2> -->
	<!-- %=session.getAttribute("userid") %> 님이 접속중입니다. -->
	
	<!-- EL 표현방식 -->
	<h2>${sessionScope.message}</h2>
	${sessionScope.userid}님이 접속중입니다.
	
	<button type="button" id="btnLogout">로그아웃</button>
</body>
</html>