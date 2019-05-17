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
	<form method="post" name="form" action="${path}/session_servlet/login.do">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>
					<input name="userid" id="userid">
					<input type="checkbox" id="chkSave"> 아이디 저장
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="password">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="로그인" id="btnLogin"/>
				</td>
			</tr>
		</table>
		
		<%
			String message = request.getParameter("message");
			String viewText = "";
			if(message != null && (message.equals("logout") || message.equals("error"))) {
				
			viewText = message.equals("logout") ? "로그아웃 되었습니다." : "정보가 일치하지 않습니다.";
		%>
			<div style="color:blue"><%=viewText %></div>
		<%	} %>
		
	</form>
</body>
</html>