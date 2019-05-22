<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#chkAll").on("click",function(){
			if($("#chkAll").prop("checked")) {
				$("input[name=num]").prop("checked", true);
			} else {
				$("input[name=num]").prop("checked", false);
			}
		});
		$("#btnAllDel").on("click",function(){
			document.form1.action = "${path}/memo_servlet/delete_all.do";
			document.form1.submit();
		});
	});
	function memo_del(num) {
		location.href="${path}/memo_servlet/delete.do?num="+num;
	}
</script>
</head>
<body>
<form method="post" name="form1">
<table border="1" class="table table-hover">
	<thead>
		<tr>
			<th><input type="checkbox" id="chkAll"></th>
			<th>번호</th>
			<th>이름</th>
			<th>메모</th>
			<th>날짜</th>
			<th><button type="button" id="btnAllDel" class="btn btn-danger">선택삭제</button></th>
		</tr>
	</thead>
	<tbody>
		<!-- var 개별변수 items 집합변수 -->
		<c:forEach var = "row" items="${list}">
			<tr>
				<td><input type="checkbox" name="num" value="${row.num}"></td>
				<td>${row.num}</td>
				<td>${row.writer}</td>
				<td>
					<a href="${path}/memo_servlet/view.do?num=${row.num}">
						${row.memo}
					</a>
				</td>
				<td>${row.post_date}</td>
				<td>
					<input type="button" value="삭제" onclick="memo_del('${row.num}')" class="btn btn-danger" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</form>
</body>
</html>