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
<table border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>메모</th>
		<th>날짜</th>
	</tr>
	<!-- var 개별변수 items 집합변수 -->
	<c:forEach var = "row" items="${list}">
		<tr>
			<td>${row.num}</td>
			<td>${row.writer}</td>
			<td>${row.memo}</td>
			<td>${row.post_date}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>