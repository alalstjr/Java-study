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
	<h1>글쓰기</h1>
	<form
		name="form1"
		method="post"
		action="${path}/board_servlet/insert.do"
		enctype="multipart/form-data"
	>
		<table border="1" width="700px">
			<tr>
				<td align="center">이름</td>
				<td><input name="writer" id="writer"/></td>
			</tr>
			<tr>
				<td align="center">제목</td>
				<td><input name="subject" id="subject" size="60"/></td>
			</tr>
			<tr>
				<td align="center">본문</td>
				<td>
					<textarea rows="5" cols="60" name="content" id="content"></textarea>	
				</td>
			</tr>
			<tr>
				<td align="center">첨부파일</td>
				<td>
					<input type="file" name="file1"> </br>	
				</td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td><input type="password" id="password" name="password"/></td>
			</tr>
			<tr>
				<td><input type="button" id="btnSave" value="확인"/></td>
			</tr>
		</table>
	</form>
</body>
</html>