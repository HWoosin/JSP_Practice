package com.myweb.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//DAO(Data Access Object)
//웹 프로그램에서 데이터베이스 CRUD작업이 요구될 때마다
//데이터베이스 접속 및 sql문 실행을 전담하는 비즈니스 로직으로 이루어진 객체
//무분별한 객체 생성을 막기 위해 싱글톤 디자인 패턴으로 구축한다.

public class UserDAO {
	
	//커넥션 풀의 정보를 담을 변수를 선언.
	private DataSource ds;
	
	private UserDAO() {
		//커넥션 풀 정보 불러오기.
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/myOracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	private static UserDAO dao = new UserDAO();
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	/////////////////////////////////////////////////////////
	
	//회원 중복 여부 확인
	public boolean confirmId(String id){
		String sql = "SELECT * FROM my_user WHERE user_id = ?";
		boolean flag = false;
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) flag = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	public void insertUser(UserVO vo) {
		String sql = "Insert into my_user values(?,?,?,?,?)";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getUserPw());
			pstmt.setString(3, vo.getUserName());
			pstmt.setString(4, vo.getUserEmail());
			pstmt.setString(5, vo.getUserAddress());
			
			pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//로그인 유효성 검증 메소드
	public int userCheck(String id, String pw) {
		String sql = "Select user_pw from my_user where user_id = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {//아이디가 있는가!
				if(!pw.equals(rs.getString(1))) {//그 아이디와 패스워드가 맞는가!
					System.out.println("비밀번호 틀림");
					return 0;
				}
				else {
					
					return 1;
				}
			}
			else {
				return -1;
			}

		}
		catch (Exception e) {
			return -2;//DB오류
		}
	}

	public UserVO getUserInfo(String id) {
		UserVO user = null;
		String sql = "select * from my_user where user_id = '"+id+"'";
	
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if(rs.next()) {
				user = new UserVO(
							rs.getString("user_id"),
							rs.getString("user_pw"),
							rs.getString("user_name"),
							rs.getString("user_email"),
							rs.getString("user_address")
						); 
				
			}
			
		} catch (Exception e) {
		}
		
		return user;
	}
	
	//비밀번호 변경 메서드
	public void changePassword(String id, String pw) {
		String sql = "Update my_user set user_pw = ? where user_id = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1,pw);
			pstmt.setString(2,id);
			
			if(pstmt.executeUpdate()==1) {
				System.out.println("비밀번호 변경완료");
			}
			
		} catch (Exception e) {
			System.out.println(id);
			System.out.println(pw);
		}
	}
	
	//회원정보 수정 메서드
	public int updateUser(UserVO vo) {
		String sql = "Update my_user set "
				+ "user_name = ? ,"
				+ "user_email = ? ,"
				+ "user_address = ? "
				+ "where user_id = ?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1,vo.getUserName());
			pstmt.setString(2,vo.getUserEmail());
			pstmt.setString(3,vo.getUserAddress());
			pstmt.setString(4,vo.getUserId());
			
			pstmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			return -2;
		}
	}
	
	//탈퇴 메서드
	public void deleteUser(String id) {
		String sql = "Delete from my_user where user_id = ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1,id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {

		}
	}

}












