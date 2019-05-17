<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

	<!-- JAVA CODE -->
	<%
		// 폼에서 입력한 데이터들은 스트링으로 처리됨
		int num = Integer.parseInt(request.getParameter("num"));
		int sum = 0;
		for(int i = 1; i <= num; i++) {
			sum += i;
		}
		out.println("합계 :"+sum); 
	%>
	
	<h2>EL&JSTL 방식</h2>
	<!-- set 은 변수 선언을 의미 -->
	<!-- set var="변수명" value="값"-->
	<c:set var="sum" value="0"/>
	
	<!-- for(int i = 1; i<= 100 ; i++) -->
	<c:forEach var="i" begin="1" end="${param.num}">
		<c:set var="sum" value="${sub+i}"/>
	</c:forEach
	>
	합계 : ${sum}
</body>
</html>