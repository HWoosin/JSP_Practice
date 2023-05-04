package com.myweb.user.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class LoginService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*
	    1. 파라미터값 얻어오기 (id, pw)
	    2. DAO 주소값 얻어오기
	    3. 로그인 유효성 검증 메서드 userCheck() 호출하기.
	    - 아이디가 없다면 스크립트 경고창 출력 후 로그인 페이지로 이동 (-1)
	    - 비밀번호가 틀렸다면 비밀번호가 틀렸다고 경고창 출력 후 뒤로가기 (0)
	    - 로그인 성공일 경우 user_mypage.jsp로 리다이렉팅 (1)
	    
	    4. 로그인 성공 시 페이지 이동 전에 getUserInfo()를 호출하여
	     로그인을 성공한 회원의 모든 정보를 받아와서 세션에 저장해 주세요.
	    ( 세션 이름: user, 저장할 값: 로그인 성공한 회원의 UserVO 객체)
	    */
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		
		response.setContentType("text/html; charset=UTF-8");
		String htmlCode;
		PrintWriter out;
		
		try {
			out = response.getWriter();
			
			if(dao.userCheck(id, pw)==-1){//return 한 값을 통해 걸러냄
				//아이디가 중복된 경우 브라우저로 바로 응답.
				htmlCode =  "<script>\r\n"
                        + "alert('없는 아이디입니다.');\r\n"
                        + "history.back();\r\n"
                        + "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
				return;
			}
			else if(dao.userCheck(id, pw)==0) {			
				htmlCode =  "<script>\r\n"
						+ "alert('비밀번호가 틀렸습니다.');\r\n"
						+ "history.back();\r\n"
						+ "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
				return;
			}
			else if(dao.userCheck(id, pw)==1) {

				
				UserVO vo = dao.getUserInfo(id);
				HttpSession session = request.getSession();
				session.setAttribute("user", vo);
				response.sendRedirect("/MyWeb/myPage.user");
//				htmlCode = "<script>\r\n"
//	                    + "location.href='/MyWeb/myPage.user';\r\n"
//	                    + "</script>";
//				out.print(htmlCode);
//				out.flush();
				return;
			}
			else {		
				htmlCode =  "<script>\r\n"
						+ "alert('잘못된 접근.');\r\n"
						+ "history.back();\r\n"
						+ "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
				return;
			}
			
			} catch (Exception e) {
				// TODO: handle exception
			}
	}

}
