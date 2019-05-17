<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
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
	// Map<key자료형, value자료형>
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("포도","grape");
	map.put("오렌지","orange");
	map.put("바나나","banana");
	map.put("사과","apple");
	request.setAttribute("map", map);
	%>
	
	<jsp:forward page="el_ex06_result.jsp"></jsp:forward>
</body>
</html>