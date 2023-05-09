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
		String sql = "select * from my_board where board_id = ?";
		BoardVO vo = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVO(
							rs.getInt("board_id"),
							rs.getString("writer"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getTimestamp("reg_date").toLocalDateTime(),
							rs.getInt("hit")
						);
			}
			
		} catch (Exception e) {
			System.out.println("글 불러오기 실패");
		}
		return vo;
	}

	@Override
	public void updateBoard(String title, String content, int bId) {

		String sql = "Update my_board set title = ?, content = ? where board_id = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bId);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void deleteBoard(int bId) {
		String sql = "Delete from my_board where board_id =?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, bId);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
