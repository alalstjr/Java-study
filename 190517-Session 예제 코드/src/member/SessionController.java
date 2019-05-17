package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session_servlet/*")
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SessionController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 요청한 주소
		String url = request.getRequestURI();
		MemberDAO dao = new MemberDAO(); // DAO 객체 생성
		
		if(url.indexOf("login.do") != -1) // 로그인 액션 
		{ 
			// 아이디, 비번을 dto에 저장
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPassword(password);
			
			// 로그인 체크
			String result = dao.loginCheckBcrypt(dto);
			String page = "/page/session_login.jsp?message=error"; // 로그인 페이지
			
			if(!result.equals("로그인 실패")) { // 로그인 성공
				HttpSession session = request.getSession(); // 세션 객체 생성
				session.setAttribute("userid", userid); // 세션변수 저장
				session.setAttribute("message", result);
				page = "/page/main.jsp"; // 로그인 성공 페이지
			}
			
			// 페이지 이동
			response.sendRedirect(request.getContextPath() + page);
		} 
			else if(url.indexOf("logout.do") != -1) // 로그인 액션
		{ 
			// 세션 초기화(전체 삭제)
			// request.getSession().invalidate();
			// 세션 객체 생성
			HttpSession session = request.getSession();
			
			// 세션값을 모두 초기화시킴;
			session.invalidate();
			
			// 페이지 이동
			String page = request.getContextPath() + "/page/session_login.jsp?message=logout";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
