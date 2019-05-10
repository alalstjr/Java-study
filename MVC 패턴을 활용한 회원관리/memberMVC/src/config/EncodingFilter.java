package config;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*") 
/*
	사용자가 어떤 요청이든 (확장자가 HTML 이든 JAVA 이든 무조건 경유하는 곳)
	예시 @WebFilter("*.do") 경우 확장자가 do 인것만 이곳을 경유하도록 할 수 있다.
*/
public class EncodingFilter implements Filter {
	final private String charset = "utf-8";
	
    public EncodingFilter() {}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		Connection conn = null;	// DB접속
		conn = DataBase.getConnection();
		if(conn != null) {
			System.out.println(conn+" DB접속 확인");
		} else {
			System.out.println("DB 접속 실패");
		}
	}

}
