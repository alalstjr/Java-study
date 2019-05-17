<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Integer countNum = (Integer)session.getAttribute("counter");
		int num = 0;
		// counter 이름의 세션값을 저장
		
		// counter란 이름의 세션값이 없으면
		if(countNum == null) {
			countNum = 1; // 초기값 저장
		} else {
			num = countNum.intValue(); // 정수로 변환
			num++;
			countNum = num;
		}
		
		// 세션값 변경
		session.setAttribute("counter", countNum);
	%>
	당신은 <%=countNum.intValue() %> 번째 방문하였습니다. <br/>
	<%
		String counter = Integer.toString(num);
	%>
	<%=counter %>
</body>
</html>