<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	// 유저의 상세페이지로 이동시켜주는 함수
	function view(userid) {
		// 현재 페이지의 폼 태그 하위의 userid 태그의 값을 변경함
		document.form01.userid.value=userid;
		
		// form 데이터를 서버에 제출
		document.form01.submit();
	}
</script>
</head>
<body>
	<%
		Map<String, Object> map = (Map<String, Object>)request.getAttribute("map");
	
		// Map 속에 존재하는 count 변수랑 list 변수를 각각의 변수와 객체에 담습니다.
		int count = (int)map.get("count");
		List<MemberDTO> items = (List<MemberDTO>)map.get("list");
	%>
	
	<form 
		name="form01" 
		action="<%=request.getContextPath()%>/member_servlet/view.do" 
		method="post"
	>
		<input type="hidden" name="userid"/> 	
	</form>
	
	등록된 회원수 : <%=count %>명
	<table border="1">
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>주소</th>
			<th>전화</th>
		</tr>
		<% for(MemberDTO dto : items) { %>
			<tr>
				<td>	
					<!-- 클릭시 유저의 상세관리 페이지로 이동 -->
					<a href="#" onclick="view('<%=dto.getUserid()%>')"><%=dto.getName()%></a>
				</td>
				<td><%=dto.getUserid()%></td>
				<td><%=dto.getPassword()%></td>
				<td><%=dto.getAddress()%></td>
				<td><%=dto.getTel()%></td> 
			</tr>
		<%} %>
	</table>
</body>
</html>