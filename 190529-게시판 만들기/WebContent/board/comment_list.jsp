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
<table border="1" width="900px">
	<c:forEach
		var="row"
		items="${list}"
	>
		<tr>
			<td>
				${row.writer}
				<!-- JSTP fmt 포멧 -->
				( <fmt:formatDate value="${row.ref_date}"
				pattern = "yyyy-MM-dd hh:mm:ss" />) <br/>
				${row.content}
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>