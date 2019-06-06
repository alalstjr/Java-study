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
	$("#btnSave").on("click", function(){
		document.form1.submit();
	});
});	
</script>
</head>
<body>
<h1>답글 달기</h1>
<form 
	name="form1" 
	method="post" 
	action="${path}/board_servlet/insertReply.do"
>
<table border = "1" width="700px">
	<tr>
		<td width="10%" align="center">이름</td>
		<td width="10%" align="center">
			<input type="text" name="writer" id="writer"/>
		</td>
	</tr>
	<tr>
		<td width="10%" align="center">제목</td>
		<td width="10%" align="center">
			<input type="text" name="subject" id="subject" value="Re:${dto.subject} size="60"/>
		</td>
	</tr>
	<tr>
		<td width="10%" align="center">본문</td>
		<td width="10%" align="center">
			<textarea id="content" name="content" rows="5" cols="60"></textarea>
		</td>
	</tr>
	<tr>
		<td width="10%" align="center">첨부파일</td>
		<td width="10%" align="center">
			<input type="file" name="file1"/>
		</td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td colspan="3">
			<input type="password" name="password" id="password"/>
		</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="num" value="${dto.num}">
			<input type="button" value="확인" id="btnSave">
		</td>
	</tr>
</table>
</form>
</body>
</html>