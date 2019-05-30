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
		$("#btnList").on("click", function(){
			location.href="${path}/board_servlet/list.do";
		});
		
		$("#btnUpdate").on("click", function(){
			document.form1.action = "${path}/board_servlet/update.do";
			document.form1.submit();
		});
		
		$("#btnDelete").on("click", function(){
			if(confirm("삭제하시겠습니까?")) {
				document.form1.action = "${path}/board_servlet/delete.do";
				document.form1.submit();
			}
		});
	});
</script>
</head>
<body>
<h1>상세화면</h1>
<form name="form1" method="post" enctype="multipart/form-data">
<table border = "1" width="700px">
	<tr>
		<td width="10%" align="center">날짜</td>
		<td width="10%" align="center">${dto.reg_date}</td>
		<td width="10%" align="center">조회수</td>
		<td width="10%" align="center">${dto.readcount}</td>
	</tr>
	<tr>
		<td align="center">이름</td>
		<td colspan="3">
			<input name="writer" id="writer" value="${dto.writer}">
		</td>
	</tr>
	<tr>
		<td align="center">제목</td>
		<td colspan="3">
			<input name="subject" id="subject" value="${dto.subject}">
		</td>
	</tr>
	<tr>
		<td align="center">본문</td>
		<td colspan="3">
			<textarea name="content" id="content">${dto.content}</textarea>
		</td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td colspan="3">
			<input type="password" name="password" id="password"/>
			<c:if test="${param.password_error == 'y'}">
				<span style="color:red">비밀번호가 일치하지 않습니다.</span>
			</c:if>
		</td>
	</tr>
	<tr>
		<td align="center">첨부파일</td>
		<td colspan="3">
			<c:if test="${dto.filesize > 0}">
				${dto.filename}
				(${dto.filesize} bytes)
				<input type="checkbox" name="fileDel"> 첨부파일 삭제
			</c:if>
			<input type="file" name="file1">
		</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="num" value="${dto.num}">
			<input type="button" value="수정" id="btnUpdate">
			<input type="button" value="삭제" id="btnDelete">
			<input type="button" value="목록" id="btnList">
		</td>
	</tr>
</table>
</form>
</body>
</html>