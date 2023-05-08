package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class RegistService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("bWriter");
		String title = request.getParameter("btitle");
		String content = request.getParameter("bcontent");
		
		BoardDAO.getInstance().regist(writer, title, content);
	}

}
