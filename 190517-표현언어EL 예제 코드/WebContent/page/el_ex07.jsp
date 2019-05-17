<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<%
	MemberDTO dto = new MemberDTO();
	dto.setUserid("jjunpro");
	dto.setName("쭌프로");
	dto.setPassword("1234");
	request.setAttribute("dto", dto);
	%>
	
	
	
	<jsp:forward page="el_ex07_result.jsp"></jsp:forward>
</body>
</html>