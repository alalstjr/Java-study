<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
	$(function(){ // 페이지가 로딩된 후 자동으로 실행되는 코드
		list("1");
	});
	function list(curPage) {
		var param = {
				"curPage" : curPage
		}
		$.ajax({
			url : "${path}/page_servlet/list.do",
			data: param,
			success : function(result){ // 콜백함수, 서버의 처리가 완료된후 실행
				$("#result").html(result); 
			}
		});
	}
</script>
</head>
<body>
	<h2>페이지 나누기</h2>
	<div id="result"></div>
</body>
</html>