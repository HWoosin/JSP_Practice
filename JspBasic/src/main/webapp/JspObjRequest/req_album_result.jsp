<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    request.setCharacterEncoding("utf-8");
    
    String name = request.getParameter("singer");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>선택하신 앨범 정보</h2><hr>
	
	<%
	if(name.equals("Without me")){%>
		<p>에미넴의 <%=name %></p>
		<iframe src="https://youtu.be/YVkUvmDQ3HY"></iframe>
	<%	
	}
	
	else{%>
	<p>헤이즈의 <%=name %></p>
	<iframe src="https://youtu.be/afxLaQiLu-o"></iframe>
	<%	
	}
	%>
	

</body>
</html>