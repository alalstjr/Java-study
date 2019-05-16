<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#nopopup").on("click", function(){
			var now = new Date();
			var val = "";
			
			if(this.checked == true) {
				val = "N"; // 7일간 팝업 안함
			} else {
				val = "Y"; // 7일간 팝업
			}
			setCookie("showCookies", val, 7);
		});
	});
	
	function setCookie(cname, cvalue, days) {
		var d = new Date();
		// 만료날짜 설정
		d.setTime(d.getTime() + (days * 24 * 60 * 60 * 1000)); // 초로 설정하는 방법
		// d.setDate(d.getDate() + days); // 날짜로 설정하는 방법
		var expires = "expires = " + d.toGMTString(); // toGMTString는 세계 표준시간
		
		// document.cookie = 쿠키변수명 = 값 ; 쿠키유효시간
		// document.cookie = showCookies = Y ; 쿠키유효시간
		document.cookie = cname + "=" + cvalue + ";" + expires;
		window.close(); // 현재 창 닫음
	}
</script>
</head>
<body>
	<input type="checkbox" id="nopopup">
	7일간 보이지 않기
</body>
</html>