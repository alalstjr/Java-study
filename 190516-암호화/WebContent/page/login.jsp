<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btnLogin").on("click", function(){
			var param = {
				"userid":$("#userid").val(),
				"password":$("#password").val()
			};
			
			$.ajax({
				type : "post",
				url : "<%=request.getContextPath()%>/member_servlet/login_bcrypt.do",
				data : param,
				success : function(result) {
					$("#result").html(result);
				}
			});
		});
	});
</script>
</head>
<body>
	아이디 : <input id="userid" /><br/>
	비밀번호 : <input type="password" id="password" /> <br/>
	<button id="btnLogin">로그인</button> <br/>
	<div id="result"></div>
</body>
</html>