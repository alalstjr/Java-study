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
	<h2>JAVA CODE 방식</h2>
	<%
	MemberDTO dto = (MemberDTO)request.getAttribute("dto");
	if(dto != null) {
		out.println("이름:" + dto.getName() + "<br/>");
		out.println("아이디:" + dto.getUserid() + "<br/>");
		out.println("비밀번호:" + dto.getPassword() + "<br/>");
	} else {
		out.println("출력할 값이 없습니다.");
	}
	%>
	
	<h2>EL 방식</h2>
	이름 : ${dto.name} <br/> <!-- getter가 호출 -->
	아이디 : ${dto.userid} <br/>
	비밀번호 : ${dto.password}
</body>
</html>