package com.myweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
		String sql = "Insert into my_board (board_id, writer, title, content)"
				+ "values(board_seq.nextval,?,?,?)";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("글 작성실패");
		}

	}

	@Override
	public List<BoardVO> listBoard() {
		String sql = "Select * from my_board order by board_id desc ";
		
		List<BoardVO> articles = new ArrayList<>();
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {
				BoardVO vo = new BoardVO(
						rs.getInt("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getTimestamp("reg_date").toLocalDateTime(),
						rs.getInt("hit")
						);
				articles.add(vo);
			}
			
		} catch (Exception e) {
			
		}
		return articles;
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
