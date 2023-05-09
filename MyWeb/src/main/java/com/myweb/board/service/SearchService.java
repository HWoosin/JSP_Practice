package com.myweb.board.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class SearchService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");
        String keyword = request.getParameter("search");

        //이 2가지를 DAO의 메서드인 searchboard에게 넘기자
        List<BoardVO> list = BoardDAO.getInstance().searchBoard(keyword, category); //주소값받고 그래야 메서드 부를 수 있으니까
        
        if(list.size() == 0) {
        	response.setContentType("text/html; charset=UTF-8");
        	try {
        	PrintWriter out = response.getWriter();
        	String htmlCode =  "<script>\r\n"
                    + "alert('검색결과가 없습니다.');\r\n"
                    + "history.back();\r\n"
                    + "</script>";
			out.print(htmlCode);
			out.flush();
			out.close();
			return;
			//조회결과가 없었다면 request에 데이터를 담지 않아도 되기 때문에 강제종료
        	}
        	catch (Exception e) {
				// TODO: handle exception
			}
        }
        request.setAttribute("boardList", list);
	}

}
