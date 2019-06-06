<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#btnWrite").on("click", function(){
			location.href="${path}/board/board_write.jsp";
		});
	});
</script>
</head>
<body>
<h1>게시판</h1>
<button 
	type="button"
	id="btnWrite"
>
	글쓰기
</button>
<table border="1" width="900px">
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>제목</td>
		<td>날짜</td>
		<td>조회수</td>
		<td>첨부파일</td>
		<td>다운로드</td>
	</tr>
	<!-- 
		JSTL 로 간단하게 forEach 문으로 출력
		 
	 	var 개별값 items 집합
	 	
	 	items의 list 는 controller의 setAttribute의 list
	 -->
	<c:forEach
		var="dto"
		items="${list}"
	>
		<tr>
			<td>${dto.num}</td>
			<td>${dto.writer}</td>
			<td>
				<a href="${path}/board_servlet/view.do?num=${dto.num}">
					${dto.subject}
				</a>
				[ ${dto.comment_count} ] 
			</td>
			<td>${dto.reg_date}</td>
			<td>${dto.readcount}</td>
			<td>
				<c:if test="${dto.filesize > 0}">
					<a href="${path}/board_servlet/download.do?num=${dto.num}">${dto.filename}</a>
				</c:if>
			</td>
			<td>${dto.down}</td>
		</tr>
	</c:forEach>
	
	<!-- 
		JSTL 을 사용하지 않은경우
		<-%@ page import="java.util.List" %>
		<-%@ page import="board.dao.BoardDAO" %>
		<-%@ page import="board.dto.BoardDTO" %>
		<-%
		List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("list");
		for(BoardDTO dto : list) {
		%>
		<tr>
			<td><-%=dto.getNum()%></td>
		</tr>
		<-%}%>
	 -->
	 
	 
</table>
</body>
</html>