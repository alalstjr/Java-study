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
<form method="post" name="form1">
<table border="1" class="table table-hover">
	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>메모</th>
			<th>날짜</th>
		</tr>
	</thead>
	<tbody>
		<!-- var 개별변수 items 집합변수 -->
		<c:forEach var = "row" items="${list}">
			<tr>
				<td>${row.num}</td>
				<td>${row.writer}</td>
				<td>${row.memo}</td>
				<td>${row.post_date}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<table border="1" class="table table-hover">
	<tr>
		<td>
			<c:if test="${curPage > 1}">
				<a href="#" onclick="list('1')">[처음]</a>
			</c:if>
			
			<!-- 처음 페이지일경우 이전 표시 안함 -->
			<c:if test="${current_block > 1}">
				<a href="#" onclick="list('${prev_page}')">[이전]</a>
			</c:if>
			
			<c:forEach var="page" begin="${block_start}" end="${block_end}">
				<c:if test="${page == curPage}">
					[${page}]
				</c:if>
				<c:if test="${page != curPage}">
					<a href="#" onclick="list('${page}')">[${page}]</a>
				</c:if>
			</c:forEach>
			
			<!-- 마지막 페이지일경우 다음 표시 안함 -->
			<c:if test="${current_block < total_block}">
				<a href="#" onclick="list('${next_page}')">[다음]</a>
			</c:if>
			
			<c:if test="${curPage < total_page}">
				<a href="#" onclick="list('${total_page}')">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>
</form>
</body>
</html>