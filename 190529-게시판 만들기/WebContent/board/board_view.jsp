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
		comment_list();
		
		$("#btnSave").on("click", function(){
			comment_add();
		});
		
		$("#btnList").on("click", function(){
			location.href="${path}/board_servlet/list.do";
		});
		
		$("#btnReply").on("click", function(){
			document.form1.action = "${path}/board_servlet/reply.do";
			document.form1.submit();
		});
		
		// 수정, 삭제 화면(편집화면)으로 이동
		$("#btnEdit").on("click", function(){
			document.form1.action = "${path}/board_servlet/pass_check.do";
			document.form1.submit();
		});
	});
	
	function comment_list() {
		$.ajax({
			type: "post",
			url: "${path}/board_servlet/commentList.do",
			data: "num=${dto.num}",
			success: function(result){
				$("#commentList").html(result);
			}
		});
	}
	function comment_add() {
		var param = {
				"board_num" : ${dto.num},
				"writer": $("#writer").val(),
				"content": $("#content").val()
		};
		
		$.ajax({
			type: "post",
			url: "${path}/board_servlet/commentAdd.do",
			data: param,
			success: function(result){ // 서버에서 글쓰기가 완료되면
				$("#writer").val(""); // 입력한 내용을 지움
				$("#content").val("");
				comment_list(); // 댓글 목록을 새로고침
			}
		});
	}
</script>
</head>
<body>
<h1>상세화면</h1>
<form name="form1" method="post">
<table border = "1" width="700px">
	<tr>
		<td width="10%" align="center">날짜</td>
		<td width="10%" align="center">${dto.reg_date}</td>
		<td width="10%" align="center">조회수</td>
		<td width="10%" align="center">${dto.readcount}</td>
	</tr>
	<tr>
		<td align="center">이름</td>
		<td colspan="3">${dto.writer}</td>
	</tr>
	<tr>
		<td align="center">제목</td>
		<td colspan="3">${dto.subject}</td>
	</tr>
	<tr>
		<td align="center">본문</td>
		<td colspan="3">${dto.content}</td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td colspan="3">
			<input type="password" name="password" id="password"/>
			<c:if test="${param.message == 'error'}">
				<span style="color:red">비밀번호가 일치하지 않습니다.</span>
			</c:if>
		</td>
	</tr>
	<tr>
		<td align="center">첨부파일</td>
		<td colspan="3">
			<c:if test="${dto.filesize > 0}">
			<a href="${path}/board_servlet/download.do?num=${dto.num}">
				${dto.filename}
				(${dto.filesize} bytes)
			</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="num" value="${dto.num}">
			<input type="button" value="수정/삭제" id="btnEdit">
			<input type="button" value="답변" id="btnReply">
			<input type="button" value="목록" id="btnList">
		</td>
	</tr>
</table>
</form>

<!-- 댓글 입력 코드 -->
<table width="700px">
	<tr>
		<td><input id="writer" placeholder="이름"/></td>
		<td>
			<button id="btnSave" type="button">확인</button>
		</td>
	</tr>
	<tr>
		<td>
			<textarea id="content" rows="5" cols="80" placeholder="내용을 입력하세요."></textarea>
		</td>
	</tr>
</table>

<!-- 댓글 목록을 출력할 영역 -->
<div id="commentList"></div>
</body>
</html>