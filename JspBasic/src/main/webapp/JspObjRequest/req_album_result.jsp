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
		<iframe width="560" height="315" src="https://www.youtube.com/embed/YVkUvmDQ3HY" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
	<%	
	}
	
	else{%>
	<p>헤이즈의 <%=name %></p>
	<iframe width="560" height="315" src="https://www.youtube.com/embed/afxLaQiLu-o" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
	<%	
	}
	%>
	

</body>
</html>