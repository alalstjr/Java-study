<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<!-- 첫번째 쿠키 다루는 코드 -->
	<%
		Cookie[] cookies = request.getCookies(); // 쿠키 배열
		
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				// if(cookies[i].getName().equals("age")) 일경우 age 값만 출력
				out.println("쿠키이름 : " + cookies[i].getName());
				out.println("쿠키값 : " + cookies[i].getValue() + "<br/>");
			}
		}
	%>
	
	<!-- 
		두번째 쿠키 다루는 코드
		
		EL(표현언어) JSESSIONID 클라이언트의 식별자 
		웹 브라우저가 다르거나 닫았다가 다시 열면 부여 새탭. 새창으로 열면 같음 
	-->
	이름 : ${cookie.name.name} ${cookie.name.value} <br/>
	나이 : ${cookie.age.name} ${cookie.age.value} <br/>
	이름 : ${param.name} <br/>
	
	<a href="deleteCookie.jsp?name=${param.name}">쿠키 삭제</a> <br/>
	<a href="editCookie.jsp">쿠키 변경</a>
</body>
</html>