<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
</head>
<body>
<!-- 
< 
response.sendRedirect(
		request.getContextPath()+"/board_servlet/list.do");
>
 -->
<c:redirect url="/board_servlet/list.do"></c:redirect>
</body>
</html>
