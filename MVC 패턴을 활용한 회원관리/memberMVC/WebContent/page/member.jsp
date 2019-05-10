<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		list();
	});
	
	function list() {
		$.ajax({
			url: "<%=request.getContextPath()%>/member_servlet/list.do",
			success: function(result) {
				$("#memberList").html(result);
			}
		});
	}
	
	function insert() {
		var param = {
			"userid" : $("#userid").val(),
			"password" : $("#password").val(),
			"name" : $("#name").val(),
			"address" : $("#address").val(),
			"tel" : $("#tel").val(),
		};
		
		$.ajax({
			type: "post",
			url: "<%=request.getContextPath()%>/member_servlet/join.do",
			data: param,
			success: function() {
				// 회원 목록을 새로 갱신합니다.
				list();
				
				// 회원가입 입력값을 초기화하여 비워줍니다.
				$("#userid").val("");
				$("#password").val("");
				$("#name").val("");
				$("#address").val("");
				$("#tel").val("");
			}
		});
	}
</script>
</head>
<body>
	<h2>회원관리</h2>
	
	아이디 <input id="userid" />
	<br/>
	비밀번호 <input type="password" id="password" />
	<br/>
	이름 <input id="name" />
	<br/>
	주소 <input id="address" />
	<br/>
	전화 <input id="tel" />
	<br/>
	<button id="btnSave" onClick="insert();">추가</button>
	
	<div id="memberList"></div>
</body>
</html>