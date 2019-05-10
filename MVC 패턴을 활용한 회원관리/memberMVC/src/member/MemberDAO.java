package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DataBase;

public class MemberDAO {
	public List<MemberDTO> memberList() {
		
		// List 객체를 인스턴스 합니다.
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		Connection conn = null;	// DB접속
		PreparedStatement pstmt = null; // SQL실행
		ResultSet rs = null; // select의 결과 처리
		
		try {
			conn = DataBase.getConnection();
			String sql = "SELECT * FROM user";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String userid = rs.getString("userid");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				
				MemberDTO dto = new MemberDTO(userid, password, name, address, tel);
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 회원 추가
	public int insert(MemberDTO dto) {
		int rows = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DataBase.getConnection();
			String sql = "INSERT INTO user" +
					" (userid, password, name, address, tel) values" +
					" (?,?,?,?,?)"
			;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getTel());
			rows = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return rows;
	}
	
	// 회원 상세페이지 view
	public MemberDTO memberDetail(String id) {
		MemberDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DataBase.getConnection();
			String sql = "SELECT * FROM user where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setTel(rs.getString("tel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	// 회원 정보수정
	public void update(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataBase.getConnection();
			String sql = "UPDATE user SET password=?, name=?, address=?, tel=?"
					+ " where userid=?"
			;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPassword());	
			pstmt.setString(2, dto.getName());	
			pstmt.setString(3, dto.getAddress());	
			pstmt.setString(4, dto.getTel());	
			pstmt.setString(5, dto.getUserid());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// 회원 삭제
 	public void delete(String userid) {
 		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataBase.getConnection();
			String sql = "DELETE FROM user WHERE userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);	
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
 	}
}
