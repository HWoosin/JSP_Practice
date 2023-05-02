<%@page import="user.User"%>
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
		User user = (User) session.getAttribute("member");
	%>
	
	<p>
		#이름: <%=user.getUserName() %> <br>
		#아이디: <%=user.getUserID() %> <br>
		#비밀번호: <%=user.getUserPW() %> <br>
		#이메일: <%=user.getUserEmail() %> 
	</p>	
	
	<hr>
	
	<p>
		#이름: ${sessionScope.member.userName} <br><%-- 정석 --%>
		#아이디: ${member.userID}  <br>
		#비밀번호: ${member.userPW}  <br>
		#이메일: ${member.userEmail}  
	</p>



</body>
</html>