<%@page import="java.net.CookieStore"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="common.Util" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 쿠키값을 불러옴
		Util u = new Util();
		String count = u.getCookie(request.getCookies(), "count");
		
		int intCount = 0;
		
		Date date = new Date();// 날짜 객체 생성
		long now_time = date.getTime(); // 현재 시각
		String visitTime = u.getCookie(
			request.getCookies(), "방문 시각"
		);
		long visit_time = 0;
		
		// 쿠키가 존재한다면
		if(visitTime != null && !visitTime.equals("")) {
			visit_time = Long.parseLong(visitTime); // String을 long 자료형으로 변경
		}
		
		// 처음 방문하면 쿠키 신규 생성
		if(count == null || count.equals("")) { // 쿠키변수가 없으면 (첫방문)
			response.addCookie(new Cookie("count", "1")); // 쿠키변수 생성
			
			// 방문시간을 쿠키에 저장
			response.addCookie(new Cookie("visit_time", Long.toString(now_time)));
			out.println("첫방문을 환영합니다.");
		} else {
			// 최근 방문시간과 현재 시간을 비교
			long period = now_time - visit_time;
			out.println("현재 :" + now_time + "<br/>");
			out.println("최근 :" + visit_time + "<br/>");
			out.println("시차 :" + period + "<br/>");
			
			intCount = Integer.parseInt(count); // String 형을 숫자로 변경
			
			if(period > 3 * 1000) {	// 특정 시간이 지나야지 쿠키값을 저장
				// 카운트 증가
				intCount++;
				response.addCookie(
					new Cookie("count", Integer.toString(intCount))	 // 쿠키 저장	
				);
				
				// 방문시간 업데이트
				response.addCookie(
					new Cookie("visit_time", Long.toString(now_time))	 // 쿠키 저장	
				);
			}
			out.println("방문 횟수:" + intCount + "<br/>");
			String counter = Integer.toString(intCount);
			
		}
	%>
</body>
</html>