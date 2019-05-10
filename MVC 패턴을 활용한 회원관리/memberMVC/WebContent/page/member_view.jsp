<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="member.MemberDTO" %>
<%
	MemberDTO dto = (MemberDTO)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	function Update() {
		document.form01.action = "<%=request.getContextPath()%>/member_servlet/update.do";
		document.form01.submit();
	}
	
	function Delete() {
		document.form01.action = "<%=request.getContextPath()%>/member_servlet/delete.do";
		document.form01.submit();
	}
</script>

<form action="" name="form01" method="post">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><%=dto.getUserid()%></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="password" value="<%=dto.getPassword()%>"/>
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="<%=dto.getName()%>"/></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" name="address" value="<%=dto.getAddress()%>"/></td>
		</tr>
		<tr>
			<td>전화</td>
			<td><input type="text" name="tel" value="<%=dto.getTel()%>"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="hidden" name="userid" value="<%=dto.getUserid()%>">
				<button type="button" id="btnUpdate" onClick="Update();">수정</button>
				<button type="button" id="btnDelete" onClick="Delete();">삭제</button>
			</td>
		</tr>
	</table>
</form>
 
</body>
</html>