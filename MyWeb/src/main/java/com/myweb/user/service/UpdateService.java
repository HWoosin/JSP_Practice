package com.myweb.user.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UpdateService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		/*
		 * form 에서 넘어오는 데이터(파라미터)들 가져오기
		 * 데이터를 UserVO로 포장
		 * dao의 updateUser()를 호출해서 수정 진행하기 
		 * 수정된 정보로 세션 데이터를 교환(덮어씌우기)
		 * alert()을 이용해서 사용자에게 응답 주고 마이페이지로 이동.
		 */
		/*
		UserVO vo = new UserVO(
				request.getParameter("id"),
				null,
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("address")
			);
		*/
		UserVO vo = new UserVO();
		vo.setUserId(request.getParameter("id"));
		vo.setUserName(request.getParameter("name"));
		vo.setUserEmail(request.getParameter("email"));
		vo.setUserAddress(request.getParameter("address"));
		
		UserDAO dao = UserDAO.getInstance();
		
		response.setContentType("text/html; charset=UTF-8");
		String htmlCode;
		PrintWriter out;
		
		try {
			out = response.getWriter();
			
			if(dao.updateUser(vo)==1) {
				
				htmlCode =  "<script>\r\n"
                        + "alert('회원정보를 수정완료했습니다.');\r\n"
                        + "location.href='/MyWeb/myPage.user';\r\n"
                        + "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
				
				vo = dao.getUserInfo(vo.getUserId());
				HttpSession session = request.getSession();
				session.setAttribute("user", vo);
//				response.sendRedirect("/MyWeb/myPage.user");
				return;
			}
			else {
				htmlCode =  "<script>\r\n"
                        + "alert('회원정보를 수정오류');\r\n"
                        + "history.back();\r\n"
                        + "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
				return;
			}
		
		}
		catch (Exception e) {

		}
		
	}

}
