<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<%
	// HashMap 은 확장 for문을 사용할 수 없기때문에 따로 key값을 fruits 담아서 사용
	HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
	String[] fruits = {"포도", "오렌지", "바나나", "사과"};
	
	for(String f : fruits) {
		out.println(f+"==>" + map.get(f) + "<br/>");
	}
	%>
	<!-- ${map.포도} 에러가 출력됩니다. 한글 변수 이기때문에 영문이면 가능-->
	${map["포도"]} <br/>
	${map["오렌지"]} <br/>
	${map["바나나"]} <br/>
	${map["사과"]} <br/>
	<c:forEach var="row" items="${map}">
		${row} : ${row.key} => ${row.value} <br/>
	</c:forEach>
</body>
</html>