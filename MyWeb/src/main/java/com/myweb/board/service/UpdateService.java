package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class UpdateService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int bId = Integer.parseInt(request.getParameter("bId"));
		String bcontent = request.getParameter("bContent");
		String btitle = request.getParameter("bTitle");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.updateBoard(btitle, bcontent, bId);
		
		//vo에 담아서 똑같이 보내기 forward 방식
//		BoardVO vo = dao.contentBoard(bId);
//		request.setAttribute("content", vo);
		
	}

}
