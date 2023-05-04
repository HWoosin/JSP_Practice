package com.myweb.user.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class ChangePwService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*
	    1. 폼 데이터 처리 (기존 비번, 변경 비번)
	    2. dao주소값을 받아오시고, userCheck()를 활용하여
	     기존 비번과 아이디 정보를 바탕으로 해당 비밀번호가 일치하는지를 검사.
	     (id는 세션에서 구해옵니다.)
	    
	    3. 기존 비밀번호가 일치한다면 비밀번호 변경 메서드 changePassword() 호출.
	    4. "비밀번호가 정상적으로 변경되었습니다." 경고창 출력 후 mypage 이동.
	    5. 현재 비밀번호가 불일치 -> "현재 비밀번호가 다릅니다." 경고창 출력 후 뒤로가기.
	    */
		UserDAO dao = UserDAO.getInstance();
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");
		String id = vo.getUserId();
		
		String oldpw = request.getParameter("oldpw");
		String newpw = request.getParameter("newpw");
		
		response.setContentType("text/html; charset=UTF-8");
		String htmlCode;
		PrintWriter out = null;
		try {
			
			
			if(dao.userCheck(id,oldpw)==1) {
				dao.changePassword(id, newpw);
				
				htmlCode =  "<script>\r\n"
                        + "alert('비밀번호가 정상적으로 변경되었습니다');\r\n"
                        + "history.back();\r\n"
                        + "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
				
				vo = dao.getUserInfo(id);
				session.setAttribute("user", vo);
				response.sendRedirect("/MyWeb/myPage.user");
				return;
			}
			else {
				htmlCode =  "<script>\r\n"
                        + "alert('현재 비밀번호가 다릅니다.');\r\n"
                        + "history.back();\r\n"
                        + "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
				return;
			}
		} catch (Exception e) {
			
		}
		
		

	}

}
