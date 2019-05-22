<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnUpdate").on("click", function(){
		var writer = $("#writer").val();
		var memo = $("#memo").val();
		
		if(writer === "") {
			alert("이름을 입력하세요");
			$("#writer").focus();
			return;
		}
		
		if(memo === "") {
			alert("메모를 입력하세요");
			$("#memo").focus();
			return;
		}
		document.form1.action = "${path}/memo_servlet/update.do";
		document.form1.submit();
	});
	
	$("#btnDelete").on("click", function(){
		list();
	});
});

</script>
</head>
<body>
<div class="container">
	<div style="height: 40px;"></div>
	<h2 class="text-center">메모 수정</h2>
	<form name="form1" id="form1" method="post">
		<table border="1" class="table">
			<tr>
				<td>이름</td>
				<!-- input 태그의 value 속성 => 태그에 표시되는 기본값 -->
				<td>
					<input type="text" name="writer" id="writer" value="${dto.writer}" required="" class="form-control input-sm" size="20" maxlength="20">
				</td>
			</tr>
			<tr>
				<td>메모</td>
				<td>
					<input type="text" name="memo" id="memo" value="${dto.memo}" required="" class="form-control input-sm" size="20" maxlength="20">
				</td>
			</tr> 
			<tr align="center">
				<td colspan="2">
					<!-- 수정 삭제할 때 num가 꼭 필요할 경우 hidden태그에 저장 -->
					<input type="hidden" name="num" id="num" value="${dto.num}">
					<input type="button" value="수정" id="btnUpdate"  class="btn btn-primary">
					<input type="button" value="삭제" id="btnDelete"  class="btn btn-danger">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>