<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%--request는 브라우저에 대한 정보, 사용자가 전달하는 값에 대한 많은 정보가 들어있습니다.
	(요정에 대한 전반적인 정보) 
	request 객체는 내장 객체입니다. 직접 생성하지 않아도 요청이 들어올 때 자동으로 객체가 생성되기 떄문에
	바로사용하면 된다.--%>
	
	URL 주소: <%=request.getRequestURL() %> <br>
	URI 주소: <%=request.getRequestURI() %> <br>
	컨텍스트 루트: <%=request.getContextPath() %> <br>
	서버이름: <%=request.getServerName() %> <br>
	포트번호: <%=request.getServerPort() %> <br>
	IP 주소: <%=request.getRemoteAddr() %> <br>

</body>
</html>