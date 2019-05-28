package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
	public static Connection getConnection() {
		try {
			final String dbURL = "jdbc:mysql://localhost:3306/tutorial?verifyServerCertificate=false&useSSL=true";
			final String dbID = "root";
			final String dbPassword = "cmd%*%&1591";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		// 오류 발생시 null 값 리턴
	}
}
