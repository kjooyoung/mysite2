package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.douzone.mysite.vo.GuestbookVo;

public class GuestbookDao {

//	public boolean insert(GuestbookVo vo) {
//		boolean result = false;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = getConnection();
//			String sql ="insert into guestbook values(null,?,?,?,sysdate())";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getPassword());
//			pstmt.setString(3, vo.getMessage());
//			
//			int count = pstmt.executeUpdate();
//			result = count == 1;
//		} catch (SQLException e) {
//			System.out.println("error:"+e);
//		} finally {
//			try {
//				if(pstmt != null)pstmt.close();
//				if(conn != null)conn.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
	
	public int insert(GuestbookVo vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql ="insert into guestbook values(null,?,?,?,sysdate())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			int count = pstmt.executeUpdate();
			
			String sql2 = "select last_insert_id()";
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean delete(long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "delete from guestbook where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public List<GuestbookVo> getList(){
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select no, name, password, message, "
					+ "date_format(reg_date,'%Y년 %m월 %d일 %H시 %i분 %s초') "
					+ "from guestbook order by reg_date desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestbookVo vo = new GuestbookVo(rs.getLong(1),
						rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public List<GuestbookVo> getList(int page){
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select no, name, password, message, "
					+ "date_format(reg_date,'%Y년 %m월 %d일 %H시 %i분 %s초') "
					+ "from guestbook order by reg_date desc "
					+ "limit ?,5";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*5);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestbookVo vo = new GuestbookVo(rs.getLong(1),
						rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public GuestbookVo getVo(long no) {
		GuestbookVo vo = new GuestbookVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from guestbook where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setMessage(rs.getString(4));
				vo.setRegDate(rs.getString(5));
			}
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return vo;
	}
	
	public String getPassword(long no) {
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select password from guestbook where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		return conn;
	}
}
