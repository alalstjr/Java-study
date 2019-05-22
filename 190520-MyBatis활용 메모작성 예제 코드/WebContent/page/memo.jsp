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
	
	// 게시글 갱신, 검색 기능 함수
	function list() {
		var param = {
			"searchkey" : $("#searchkey").val(),
			"search" : $("#search").val()
		}
		
		$.ajax({
			type : "post",
			data : param,
			url : "${path}/memo_servlet/list.do",
			success : function(result){
				$("#result").html(result);
			}
		});
	}
	
	// 게시글 추가 함수
	function insert() {
		var writer = $("#writer").val();
		var memo = $("#memo").val();
	    var param = {
	        "writer" : writer,
	    	"memo" : memo
	    }
		
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
<div class="container">
	<div style="height: 40px;"></div>
	<div class="form-box"> 
		<div class="form-group has-feedback">
			<label for="writer">
				<b>이름</b>
			</label>
			<input type="text" name="writer" id="writer" required="" class="form-control input-sm" size="20" maxlength="20">
		</div>
		<div class="form-group has-feedback">
			<label for="memo">
				<b>메모</b>
			</label>
			<input type="text" name="memo" id="memo" required="" class="form-control input-sm" size="20" maxlength="20">
		</div>
		<div class="form-group has-feedback">
			<button type="button" id="btnSave" class="btn btn-primary">메모작성</button>
		</div>
		
		<div class="clearfix"></div>
		
		<div class="form-group row">
			<div class="col-sm-2 float-left">
				<select id="searchkey" class="form-control">
					<option value="writer">이름</option>
					<option value="memo">메모</option>
					<option value="writer_memo">이름 + 메모</option>
				</select>
			</div>
			<div class="col-sm-9 float-left">
				<input type="text" name="search" id="search" required="" class="form-control input-sm" size="20" maxlength="20" value="${search}">
			</div>
			<div class="col-sm-1 float-left">
				<input type="button" id="btnSearch" class="btn btn-primary" value="조회"/> <br/>
			</div>
		</div>
		
		<div class="clearfix"></div>
		
		<div id="result"></div>
	</div>
</div>
</body>
</html>