package com.myweb.user.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class DeleteService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		UserDAO dao = UserDAO.getInstance();
		response.setContentType("text/html; charset=UTF-8");
		String htmlCode;
		PrintWriter out;
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");
		String id = vo.getUserId();
		
		String pw = request.getParameter("check_pw");
		try {
			out = response.getWriter();
			
			if(dao.userCheck(id,pw)==1) {
				dao.deleteUser(id);
				request.getSession().invalidate();
				htmlCode =  "<script>\r\n"
                        + "alert('탈퇴를 완료했습니다.');\r\n"
                        + "location.href='/MyWeb';\r\n"
                        + "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
			}
			else {
				htmlCode =  "<script>\r\n"
                        + "alert('비밀번호가 틀립니다.');\r\n"
                        + "history.back();\r\n"
                        + "</script>";
				out.print(htmlCode);
				out.flush();
				out.close();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
