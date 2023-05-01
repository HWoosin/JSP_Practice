<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Cookie[] cookies = request.getCookies();
	String userID = null;
	if (cookies != null) {
		for (Cookie c : cookies) {
			if (c.getName().equals("login_cookie")) {
				userID = c.getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if (userID != null) {
	%>
		<p>
			<%=userID%>님 환영합니다.
		</p>
		<a href="cookie_login.jsp">로그인 화면으로</a>
	<%
		} 
		else {//애초에 로그인을 안했거나, 로그인 시간이 만료
	%>
		<p>시간이 지나 자동으로 로그아웃 처리되었습니다.</p>
		<a href="cookie_login.jsp">로그인 화면으로</a>
	<%
		}
	%>


</body>
</html>