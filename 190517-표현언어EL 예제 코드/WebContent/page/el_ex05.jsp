<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
		List<String> items = new ArrayList<String>();
		items.add("오렌지");
		items.add("사과");
		items.add("포도");
		items.add("복숭아");
		request.setAttribute("items", items);
	%>
	<jsp:forward page="el_ex05_result.jsp">
</body>
</html>