package com.myweb.board.model;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO implements IBoardDAO {

	//커넥션 풀의 정보를 담을 변수를 선언.
	private DataSource ds;
		
		private BoardDAO() {
			//커넥션 풀 정보 불러오기.
			try {
				InitialContext ct = new InitialContext();
				ds = (DataSource)ct.lookup("java:comp/env/jdbc/myOracle");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
		}
		
		private static BoardDAO dao = new BoardDAO();
		public static BoardDAO getInstance() {
			if(dao == null) {
				dao = new BoardDAO();
			}
			return dao;
		}
	
	@Override
	public void regist(String writer, String title, String content) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardVO> listBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO contentBoard(int bId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBoard(String title, String content, int bId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBoard(int bId) {
		// TODO Auto-generated method stub

	}

}
