<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- WebContent/include/session_check.jsp -->
<!-- 태그라이브러리 선언 -->
<!-- taglib prefix="태그의 접두어" uri="태그의 식별자"-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.userid == null}">
	<script>
		alert("로그인하신 후 사용하세요.");
		location.href="${path}/page/session_login.jsp";
	</script>
</c:if>
