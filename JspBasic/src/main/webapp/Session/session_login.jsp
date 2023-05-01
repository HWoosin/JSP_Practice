<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String id = (String)session.getAttribute("userID");
    String nick = (String)session.getAttribute("userNick");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%if(id == null){%>
		<form action="session_login_con.jsp" method="post">
            <input type="text" name="id" size="10" placeholder="ID"> <br>
            <input type="password" name="pw" size="10" placeholder="PW"> <br>
            <input type="text" name="nick" size="10" placeholder="별명"> <br>
            <input type="submit" value="로그인">
        </form>
	<%}
	else{%>
		<p>이미 로그인 중입니다.</p>
		<a href="../JspObjRequest/req_album.jsp">앨범 페이지</a>
	<% }%>
</body>
</html>