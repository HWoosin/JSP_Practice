<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="req_album_result.jsp">
	<table border="1">
	<th></th>
	<th>앨범커버</th>
	<th>가수</th>
	<th>앨범제목</th>
	<th>발매일</th>
	<tr align="center">
		<td><input type="radio" name= "singer" value="Without me"></td>
	    <td><img src="./에미넴.jpg" alt="에미넴" width="80px" height="100px"></td>
	    <td>에미넴</td>
	    <td>Without me</td>
	    <td>2002</td>
	</tr>
	<tr align="center">
		<td><input type="radio" name= "singer" value="비도 오고 그래서"></td>
	    <td><img src="./헤이즈.jpg" alt="헤이즈" width="100px" height="100px"></td>
	    <td>헤이즈</td>
	    <td>비도 오고 그래서</td>
	    <td>2017</td>
	</tr>
	<tr align="center">
		<td colspan="5"><input type="submit" value="확인"></td>
	</tr>
    </table>
	</form>

</body>
</html>