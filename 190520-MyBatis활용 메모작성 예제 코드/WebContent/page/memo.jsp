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
		list();
		$("#btnSave").on("click", function(){
			insert();
		});
		$("#btnSearch").on("click", function(){
			list();
		});
	});
	
	function list() {
		var param = "searchkey" + $("#searchkey").val() + "&search=" + $("#search").val();
		
		$.ajax({
			type : "post",
			data : param,
			url : "${path}/memo_servlet/list.do",
			success : function(result){
				$("#result").html(result);
			}
		});
	}
	
	function insert() {
		var writer = $("#writer").val();
		var memo = $("#memo").val();
		var param = "writer=" + writer + "&memo=" + memo;
		
		$.ajax({
			type : "post",
			data : param,
			url : "${path}/memo_servlet/insert.do",
			success : function(){
				list("search=");
				$("#writer").val("");
				$("#memo").val("");
			}
		});
	}
</script>
</head>
<body>
이름 : <input id="writer" size="10"/> <br/>
메모 : <input id="memo" size="40"/> <br/>
<input type="button" id="btnSave" value="확인"/> <br/>

<select id="searchkey">
	<option value="writer">이름</option>
	<option value="memo">메모</option>
	<option value="writer_memo">이름 + 메모</option>
</select>

<input type="text" id="search" value="${search}"/> <br/>
<input type="button" id="btnSearch" value="조회"/> <br/>
<div id="result"></div>

</body>
</html>