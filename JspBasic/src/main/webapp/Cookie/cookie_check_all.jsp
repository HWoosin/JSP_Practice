<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	Cookie[] cookies = request.getCookies();
    if(cookies != null) {
		for(Cookie c : cookies){
			//쿠키의 이름을 얻어오는 메서드 getName()
			out.print(c.getName()+" : "+c.getValue()+"<br>");
			out.print("------------------------------------<br>");
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

</body>
</html>