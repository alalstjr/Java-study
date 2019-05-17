package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DataBase;
import crypt.BCrypt;

public class MemberDAO {
	
 	// Bcrypt 방식 회원 등록
 	public int insertBcrypt(MemberDTO dto) {
 		int rows = 0;
 		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataBase.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into user ");
			sql.append("(userid, password, name) values ");
			sql.append("(?,?,?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUserid());
			// 실행할 때마다 암호화키가 변경됨
			// BCrypt.hashpw( 암호화할 평문, 암호화키)
			
			String password = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
			
			System.out.println("평문 : " + dto.getPassword());
			System.out.println("암호화된 텍스트 : " + password);
			
			pstmt.setString(2, password);
			pstmt.setString(3, dto.getName());
			rows=pstmt.executeUpdate();
			
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
 	
 	// Bcrypt 방식의 로그인
 	public String loginCheckBcrypt(MemberDTO dto) {
 		String result = "";
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		try {
 			conn = DataBase.getConnection();
 			String sql = "SELECT * FROM user where userid=? ";
 			pstmt = conn.prepareStatement(sql);
 			pstmt.setString(1, dto.getUserid());
 			rs = pstmt.executeQuery();
 			if(rs.next()) { // id 가 맞는경우
 				String password = rs.getString("password");
 				
 				// checkpw( 평문, 암호문 ) => 맞으면 true, 틀리면 false
 				if(BCrypt.checkpw(dto.getPassword(), password)) { // 비밀번호가 맞는경우
 					System.out.println(result+"결과");
 					result = rs.getString("name") + "님 환영합니다.";
 				} else {
 					result = "로그인 실패";
 				}
 			} else {
 				result = "로그인 실패";
 			}
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
 		
 		return result;
 	}
}
