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
		$("#btnJoin").on("click", function(){
			var param = {
				"userid":$("#userid").val(),
				"password":$("#password").val(),
				"name":$("#name").val()
			};
			
			$.ajax({
				type : "post",
				url : "<%=request.getContextPath()%>/member_servlet/join_bcrypt.do",
				data : param,
				success : function(result) {
					alert("추가되었습니다.");
				}
			});
		});
	});
</script>
</head>
<body>
	아이디 : <input id="userid" /><br/>
	비밀번호 : <input type="password" id="password" /> <br/>
	이름 : <input id="name" /> <br/>
	<button id="btnJoin">회원가입</button> <br/>
</body>
</html>