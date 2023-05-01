<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("utf-8");
    
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw");
    	
    	if(id.equals("abc1234") && pw.equals("aaa1111")){//로그인 검사 로직
    		//로그인 되면 쿠키생성
    		Cookie loginCoo = new Cookie("login_cookie", id);
    		loginCoo.setMaxAge(5);
    		response.addCookie(loginCoo);//응답과 함꼐 값을 보내버림
    		
    		//사용자가 아이디 기억하기 체크박스를 체크했는지의 여부 확인
    		if(request.getParameter("id_remember")!= null){
    			Cookie idMem = new Cookie("remember_id", id);
    			idMem.setMaxAge(30);
    			response.addCookie(idMem);
    		}
    		
    		response.sendRedirect("cookie_welcome.jsp");
    		
    	}
    	else{
    		response.sendRedirect("cookie_login.jsp");
    	}
    %>
