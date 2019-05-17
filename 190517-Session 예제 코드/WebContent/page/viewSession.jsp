<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 세션 수정 method는 별도로 없음
		// session.setAttribute("id", "jjunpro");
		Enumeration<String> attr = session.getAttributeNames(); // 집합 객체
		
		while(attr.hasMoreElements()) { // 다음 요소가 있으면
			// 다음 요소
			String key = attr.nextElement();
			// session.getAttribute("세션변수명") 세션값 조회
			// session 은 모든 값을 받을 수 있기때문에 자료형이 Object 
			Object value = session.getAttribute(key);
			out.println("세션변수명 : " + key);
			out.println(", 세션값 : " + value + "<br/>");
		}
		
		String id = (String) session.getAttribute("id");
		int age = (Integer) session.getAttribute("age");
		Object a = session.getAttribute("age");
		
		// 값을 수정 하는 방법 
		session.setAttribute("id", "변경된 값");
	%>
	아이디 : <%=session.getAttribute("id") %> <br/>
	아이디 : ${sessionScope.id} <br/>
	비밀번호 : ${sessionScope.password}<br/>
	나이 : ${sessionScope.age} <br/>
	세션ID : <%=session.getId() %> 
	
	<a href="deleteSession.jsp">세션 삭제</a>
</body>
</html>